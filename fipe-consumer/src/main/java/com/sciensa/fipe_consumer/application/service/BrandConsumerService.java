package com.sciensa.fipe_consumer.application.service;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sciensa.fipe_consumer.application.dto.BrandResponse;
import com.sciensa.fipe_consumer.application.model.Brand;
import com.sciensa.fipe_consumer.application.model.Model;
import com.sciensa.fipe_consumer.application.repository.BrandRepository;
import com.sciensa.fipe_consumer.application.repository.ModelRepository;

@Service
public class BrandConsumerService {
  private final FipeClientService fipeClient;
  private final BrandRepository brandRepository;
  private final ModelRepository modelRepository;

  public BrandConsumerService(FipeClientService fipeClient, BrandRepository brandRepository, ModelRepository modelRepository) {
      this.fipeClient = fipeClient;
      this.brandRepository = brandRepository;
      this.modelRepository = modelRepository;
  }

  @KafkaListener(topics="${app.kafka.topics.brands}", groupId = "fipe-consumer-group")
  public void consume(BrandResponse message) {
    System.out.println("marca: " + message);

    Brand brand = brandRepository.findByCode(message.codigo())
            .orElseGet(() -> {
                Brand b = new Brand();
                b.setCode(message.codigo());
                b.setName(message.nome());
                return brandRepository.save(b);
            });

    fipeClient.getModels(brand.getCode())
            .subscribe(response -> {
                List<Model> models = response.modelos().stream()
                        .map(m -> {
                            Model model = new Model();
                            model.setCode(m.codigo());
                            model.setName(m.nome());
                            model.setBrand(brand);
                            return model;
                        }).toList();
                modelRepository.saveAll(models);

                System.out.println("Modelos da marca: " + brand.getName());
            });
  }
}

