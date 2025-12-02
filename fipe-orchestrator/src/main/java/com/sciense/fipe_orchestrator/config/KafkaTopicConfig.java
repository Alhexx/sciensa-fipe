package com.sciense.fipe_orchestrator.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${app.kafka.topics.brands}")
    private String brandsTopic;

    @Bean
    NewTopic brandsTopic() {
        return TopicBuilder.name(brandsTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}