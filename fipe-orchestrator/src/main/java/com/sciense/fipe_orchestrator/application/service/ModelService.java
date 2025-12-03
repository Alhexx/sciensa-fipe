package com.sciense.fipe_orchestrator.application.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sciense.fipe_orchestrator.application.dto.ModelApiDTO;
import com.sciense.fipe_orchestrator.application.dto.ModelApiUpdate;

import reactor.core.publisher.Mono;

@Service
public class ModelService {
  private final WebClient fipeConsumerApiClient;
  
  public ModelService(WebClient fipeConsumerApiClient) {
    this.fipeConsumerApiClient = fipeConsumerApiClient;
  }

  @Cacheable(value = "modelosPorMarca", key = "#brandNameOrCode")
  public List<ModelApiDTO> getModelByBrandNameOrCode(String brandNameOrCode) {
      System.out.println("Não usando cache");

      return fipeConsumerApiClient.get()
            .uri("/models/{brandNameOrCode}", brandNameOrCode)
            .retrieve()
            .bodyToFlux(ModelApiDTO.class)
            .collectList()
            .onErrorResume(e -> {
                return Mono.error(new RuntimeException("Falha ao buscar modelos na API2.", e));
            }).block();
  }

  @CacheEvict(value = "modelosPorMarca", key = "#code")
  public ModelApiDTO updateModel(String code, ModelApiUpdate update) {
      System.out.println("Limpando cache");
      
      return fipeConsumerApiClient.patch()
            .uri("/models/{code}", code)
            .bodyValue(update)
            .retrieve()
            .bodyToMono(ModelApiDTO.class)
            .onErrorResume(e -> {
                return Mono.error(new RuntimeException("Falha ao atualizar modelo na API-2.", e));
            }).block();
  }
}
