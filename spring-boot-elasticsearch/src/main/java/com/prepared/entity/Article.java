package com.prepared.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;

@Document(indexName = "blog", type = "article")
public class Article {

    @Id
    private String id;
    
    private String title;
    
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Author> authors;

    @Field(type = Keyword)
    private String[] tags;

    public Article() {
    }

    public Article(String title) {
        this.title = title;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String... tags) {
        this.tags = tags;
    }
}