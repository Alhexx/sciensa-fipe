package com.sciensa.fipe_consumer.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sciensa.fipe_consumer.application.dto.FipeModelsResponse;

import reactor.core.publisher.Mono;

@Service
public class FipeClientService {

    private final WebClient webClient;

    public FipeClientService(WebClient fipeWebClient) {
        this.webClient = fipeWebClient;
    }

    public Mono<FipeModelsResponse> getModels(String brandCode) {
        return webClient.get()
                .uri("/carros/marcas/{brandCode}/modelos", brandCode)
                .retrieve()
                .bodyToMono(FipeModelsResponse.class);
    }
}
