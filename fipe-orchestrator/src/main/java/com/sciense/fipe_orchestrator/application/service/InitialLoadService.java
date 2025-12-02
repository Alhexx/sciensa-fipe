package com.sciense.fipe_orchestrator.application.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InitialLoadService {

    private final FipeClientService fipeClient;
    private final BrandProducerService producerService;

    public InitialLoadService(FipeClientService fipeClient,
                              BrandProducerService producerService) {
        this.fipeClient = fipeClient;
        this.producerService = producerService;
    }

    public Mono<Void> executeInitialLoad() {
        return fipeClient.getBrands()
                .flatMapMany(Flux::fromIterable)
                .doOnNext(producerService::sendBrand)
                .then();
    }
}
