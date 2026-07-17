package com.christian.desafio.experience.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    WebClient gorestWebClient(WebClient.Builder builder,
                              @Value("${clients.gorest.base-url}") String baseUrl) {
        return builder.baseUrl(baseUrl).build();
    }

    @Bean
    WebClient supportWebClient(WebClient.Builder builder,
                               @Value("${clients.support.base-url}") String baseUrl) {
        return builder.baseUrl(baseUrl).build();
    }
}
