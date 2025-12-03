package com.sciense.fipe_orchestrator.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sciense.fipe_orchestrator.application.dto.fipe.BrandResponse;

@Service
public class BrandProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;

    public BrandProducerService(KafkaTemplate<String, Object> kafkaTemplate, @Value("${app.kafka.topics.brands}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @CacheEvict(value = "marcas", key = "'all'")
    public void sendBrand(BrandResponse brand) {
        System.out.println("Enviando marca: " + brand);
        System.out.println("Limpando cache");
        kafkaTemplate.send(topic, brand.codigo(), brand);
    }
}
