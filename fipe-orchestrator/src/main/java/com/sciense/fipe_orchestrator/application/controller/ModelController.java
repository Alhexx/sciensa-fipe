package com.sciense.fipe_orchestrator.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciense.fipe_orchestrator.application.dto.ModelApiDTO;
import com.sciense.fipe_orchestrator.application.dto.ModelApiUpdate;
import com.sciense.fipe_orchestrator.application.service.ModelService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/models")
public class ModelController {
    
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/{brandNameOrCode}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<ModelApiDTO>> getModels(@PathVariable String brandNameOrCode) {
      List<ModelApiDTO> modelos = modelService.getModelByBrandNameOrCode(brandNameOrCode);
      return ResponseEntity.ok(modelos);
    }

    @PatchMapping("/{code}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ModelApiDTO> updateModel(@PathVariable String code, @RequestBody ModelApiUpdate update) {
      ModelApiDTO updated = modelService.updateModel(code, update);
      return ResponseEntity.ok(updated);
    }
}