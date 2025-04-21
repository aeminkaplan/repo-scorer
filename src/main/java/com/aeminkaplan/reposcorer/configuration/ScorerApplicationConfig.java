package com.aeminkaplan.reposcorer.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class ScorerApplicationConfig {

    @Value("${cache.duration.in.minutes:10}")
    private Integer cacheDuration;

    @Value("${cache.size:100}")
    private Integer cacheSize;


    @Bean
    RestClient restClient(){
        return RestClient.builder()
                .baseUrl("https://api.github.com")
                .build();
    }
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager("repos");
        manager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(cacheDuration))
                .maximumSize(cacheSize));
        return manager;
    }
}
