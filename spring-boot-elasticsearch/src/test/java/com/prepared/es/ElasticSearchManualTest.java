package com.prepared.es;

import com.prepared.config.ElasticsearchConfig;
import com.prepared.dao.ArticleRepository;
import com.prepared.entity.Article;
import com.prepared.entity.Author;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static java.util.Arrays.asList;
import static org.elasticsearch.index.query.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This Manual test requires: Elasticsearch instance running on localhost:9200.
 * 
 * The following docker command can be used: docker run -d --name es762 -p
 * 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ElasticsearchConfig.class)
public class ElasticSearchManualTest {

    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Resource
    private ArticleRepository articleRepository;

    private final Author johnSmith = new Author("John Smith");
    private final Author johnDoe = new Author("John Doe");

    /**
     * 往es中插入数据
     */
    @Before
    public void before() {
        Article article = new Article("Spring Data Elasticsearch");
        article.setAuthors(asList(johnSmith, johnDoe));
        article.setTags("elasticsearch", "spring data");
        articleRepository.save(article);

        article = new Article("Search engines");
        article.setAuthors(asList(johnDoe));
        article.setTags("search engines", "tutorial");
        articleRepository.save(article);

        article = new Article("Second Article About Elasticsearch");
        article.setAuthors(asList(johnSmith));
        article.setTags("elasticsearch", "spring data");
        articleRepository.save(article);

        article = new Article("Elasticsearch Tutorial");
        article.setAuthors(asList(johnDoe));
        article.setTags("elasticsearch");
        articleRepository.save(article);
    }

    /**
     * 执行完测试用例之后，删除所有的数据
     */
    @After
    public void after() {
        articleRepository.deleteAll();
    }

    @Test
    public void givenArticleService_whenSaveArticle_thenIdIsAssigned() {
        final List<Author> authors = asList(new Author("John Smith"), johnDoe);

        Article article = new Article("Making Search Elastic");
        article.setAuthors(authors);

        article = articleRepository.save(article);
        assertNotNull(article.getId());
    }

    @Test
    public void givenPersistedArticles_whenSearchByAuthorsName_thenRightFound() {
        final Page<Article> articleByAuthorName = articleRepository.findByAuthorsName(johnSmith.getName(), PageRequest.of(0, 10));
        assertEquals(2L, articleByAuthorName.getTotalElements());
    }

    @Test
    public void givenCustomQuery_whenSearchByAuthorsName_thenArticleIsFound() {
        final Page<Article> articleByAuthorName = articleRepository.findByAuthorsNameUsingCustomQuery("Smith", PageRequest.of(0, 10));
        assertEquals(2L, articleByAuthorName.getTotalElements());
    }

    @Test
    public void givenTagFilterQuery_whenSearchByTag_thenArticleIsFound() {
        final Page<Article> articleByAuthorName = articleRepository.findByFilteredTagQuery("elasticsearch", PageRequest.of(0, 10));
        assertEquals(3L, articleByAuthorName.getTotalElements());
    }

    @Test
    public void givenTagFilterQuery_whenSearchByAuthorsName_thenArticleIsFound() {
        final Page<Article> articleByAuthorName = articleRepository.findByAuthorsNameAndFilteredTagQuery("Doe", "elasticsearch", PageRequest.of(0, 10));
        assertEquals(2L, articleByAuthorName.getTotalElements());
    }

    @Test
    public void givenPersistedArticles_whenUseRegexQuery_thenRightArticlesFound() {
        final Query searchQuery = new NativeSearchQueryBuilder().withFilter(regexpQuery("title", ".*data.*"))
            .build();

        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        assertEquals(1, articles.getTotalHits());
    }

    @Test
    public void givenSavedDoc_whenTitleUpdated_thenCouldFindByUpdatedTitle() {
        final Query searchQuery = new NativeSearchQueryBuilder().withQuery(fuzzyQuery("title", "serch"))
            .build();
        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        assertEquals(1, articles.getTotalHits());

        final Article article = articles.getSearchHit(0).getContent();
        final String newTitle = "Getting started with Search Engines";
        article.setTitle(newTitle);
        articleRepository.save(article);

        assertEquals(newTitle, articleRepository.findById(article.getId())
            .get()
            .getTitle());
    }

    @Test
    public void givenSavedDoc_whenDelete_thenRemovedFromIndex() {
        final String articleTitle = "Spring Data Elasticsearch";

        final Query searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("title", articleTitle).minimumShouldMatch("75%"))
            .build();
        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));

        assertEquals(1, articles.getTotalHits());
        final long count = articleRepository.count();

        articleRepository.delete(articles.getSearchHit(0).getContent());

        assertEquals(count - 1, articleRepository.count());
    }

    @Test
    public void givenSavedDoc_whenOneTermMatches_thenFindByTitle() {
        final Query searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("title", "Search engines").operator(AND))
            .build();
        final SearchHits<Article> articles = elasticsearchTemplate.search(searchQuery, Article.class, IndexCoordinates.of("blog"));
        assertEquals(1, articles.getTotalHits());
    }
}
