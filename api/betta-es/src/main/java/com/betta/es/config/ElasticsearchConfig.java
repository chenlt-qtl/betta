package com.betta.es.config;

import com.betta.common.config.BettaConfig;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(@Autowired BettaConfig bettaConfig){
        RestClientBuilder builder = RestClient.builder(new HttpHost(bettaConfig.getEsHost(),bettaConfig.getEsPort()));
        return new RestHighLevelClient(builder);
    }
}
