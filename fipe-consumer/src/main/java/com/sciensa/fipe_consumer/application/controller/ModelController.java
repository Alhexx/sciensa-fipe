package com.sciensa.fipe_consumer.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciensa.fipe_consumer.application.dto.ModelApiDTO;
import com.sciensa.fipe_consumer.application.dto.ModelApiUpdate;
import com.sciensa.fipe_consumer.application.service.ModelService;


@RestController
@RequestMapping("/models")
public class ModelController {
  private final ModelService modelService;

  public ModelController(ModelService modelService) {
    this.modelService = modelService;
  }

  @GetMapping("/{brandNameOrCode}")
  public ResponseEntity<List<ModelApiDTO>> getMethodName(@PathVariable String brandNameOrCode) {
      return ResponseEntity.ok(modelService.getModelByBrandNameOrCode(brandNameOrCode));
  }

  @PatchMapping("/{code}")
  public ResponseEntity<ModelApiDTO> updateModel(
          @PathVariable String code,
          @RequestBody ModelApiUpdate update
  ) {
      ModelApiDTO updated = modelService.updateModel(code, update);
      return ResponseEntity.ok(updated);
  }
}
