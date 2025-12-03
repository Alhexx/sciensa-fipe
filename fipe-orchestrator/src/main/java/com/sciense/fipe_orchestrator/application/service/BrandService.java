package com.sciense.fipe_orchestrator.application.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sciense.fipe_orchestrator.application.dto.fipe.BrandResponse;

import reactor.core.publisher.Mono;

@Service
public class BrandService {
  private final WebClient fipeConsumerApiClient;

  public BrandService(WebClient fipeConsumerApiClient) {
    this.fipeConsumerApiClient = fipeConsumerApiClient;
  }
  
  @Cacheable(value = "marcas", key = "'all'")
  public List<BrandResponse> getBrands() {
      System.out.println("Não usando cache");

      return fipeConsumerApiClient.get()
            .uri("/brands")
            .retrieve()
            .bodyToFlux(BrandResponse.class)
            .collectList()
            .onErrorResume(e -> {
                return Mono.error(new RuntimeException("Falha ao buscar marcas na API2.", e));
            }).block();
  }
}
