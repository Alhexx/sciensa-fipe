package com.sciense.fipe_orchestrator.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sciense.fipe_orchestrator.application.dto.fipe.BrandResponse;

import reactor.core.publisher.Mono;

@Service
public class FipeClientService {

    private final WebClient webClient;

    public FipeClientService(WebClient fipeWebClient) {
        this.webClient = fipeWebClient;
    }

    public Mono<List<BrandResponse>> getBrands() {
        return webClient.get()
                .uri("/carros/marcas")
                .retrieve()
                .bodyToFlux(BrandResponse.class)
                .collectList();
    }
}
