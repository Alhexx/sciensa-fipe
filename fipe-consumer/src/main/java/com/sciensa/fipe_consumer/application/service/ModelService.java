package com.sciensa.fipe_consumer.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sciensa.fipe_consumer.application.dto.ModelApiDTO;
import com.sciensa.fipe_consumer.application.dto.ModelApiUpdate;
import com.sciensa.fipe_consumer.application.model.Model;
import com.sciensa.fipe_consumer.application.repository.ModelRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ModelService {
  private final ModelRepository modelRepository;

  public ModelService(ModelRepository modelRepository) {
    this.modelRepository = modelRepository;
  }

  public List<ModelApiDTO> getModelByBrandNameOrCode(String brandNameOrCode) {
    return modelRepository.searchByBrandNameOrCode(brandNameOrCode).stream()
                .map(model -> new ModelApiDTO(model.getCode(), model.getName(), model.getObservations(), model.getBrand().getName()))
                .toList();
  }

  @Transactional
  public ModelApiDTO updateModel(String code, ModelApiUpdate update) {
    Model model = modelRepository.findByCode(code)
        .orElseThrow(() -> new EntityNotFoundException(
            "Modelo não encontrado com o código: " + code
        ));

    if (update.nome() != null && !update.nome().isBlank()) {
        model.setName(update.nome());
    }

    if (update.observacoes() != null) {
        model.setObservations(update.observacoes());
    }

    modelRepository.save(model);

    return new ModelApiDTO(
        model.getCode(),
        model.getName(),
        model.getObservations(),
        model.getBrand().getName() 
    );
  }
}
