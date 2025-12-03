package com.sciense.fipe_orchestrator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  @Bean
  WebClient fipeWebClient(WebClient.Builder builder, @Value("${external.fipe.url}") String baseUrl) {
    return builder.baseUrl(baseUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  @Bean
  WebClient fipeConsumerApiClient(WebClient.Builder builder, @Value("${external.fipe-consumer-api.url}") String baseUrl) {
    return builder.baseUrl(baseUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }
}
