package com.fast.pay.read.config;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponseInterceptor;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages
        = "com.fast.pay.read.repository")
public class ElasticSearchConfig extends
        AbstractElasticsearchConfiguration {

//    @Override
//    public ClientConfiguration clientConfiguration() {
//        return ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .build();
//    }

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration =
                ClientConfiguration
                        .builder()
                        .connectedTo("localhost:9200")
                        .build();

        return RestClients.create(clientConfiguration).rest();
    }
    private RestClientBuilder.HttpClientConfigCallback httpClientConfigCallback = httpClientBuilder ->
            httpClientBuilder
                    .setDefaultCredentialsProvider(null)
                    // this request & response header manipulation helps get around newer (>=7.16) versions
                    // of elasticsearch-java client not working with older (<7.14) versions of Elasticsearch
                    // server
                    .addInterceptorLast(
                            (HttpResponseInterceptor)
                                    (response, context) ->
                                            response.addHeader("X-Elastic-Product", "Elasticsearch"));

//    @Bean
//    public RestClient restClient() {
//        return RestClient.builder(
//                new HttpHost("localhost", 9200, "http")).setHttpClientConfigCallback(
//                        this.httpClientConfigCallback
//        ).build();
//
//    }



}