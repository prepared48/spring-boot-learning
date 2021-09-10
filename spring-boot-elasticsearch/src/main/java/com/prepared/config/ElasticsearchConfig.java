package com.prepared.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.prepared.dao")
@ComponentScan(basePackages = { "com.prepared.dao" })
public class ElasticsearchConfig {

//    @Value("${tpse.es.cluster.ipAndPorts}")
//    String ipAndPortsStr;
//
//    Pattern p = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)\\:(\\d+)");
//
//    @Bean(name = "restHighLevelClient")
//    public RestHighLevelClient getClient() {
//        String[] ipAndPorts = ipAndPortsStr.trim().split(",");
//        HttpHost[] hosts = new HttpHost[ipAndPorts.length];
//        for (int i = 0; i < ipAndPorts.length; i++) {
//            Matcher m = p.matcher(ipAndPorts[i]);
//            m.find();
//            HttpHost httpHost = new HttpHost(m.group(1), Integer.parseInt(m.group(2)), "http");
//            hosts[i] = httpHost;
//        }
//        return new RestHighLevelClient(RestClient.builder(hosts));
//    }

    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration
            = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}