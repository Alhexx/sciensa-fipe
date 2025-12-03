package com.sciense.fipe_orchestrator.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciense.fipe_orchestrator.application.dto.fipe.BrandResponse;
import com.sciense.fipe_orchestrator.application.service.BrandService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
  private final BrandService brandService;

  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<List<BrandResponse>> getModels() {
    List<BrandResponse> modelos = brandService.getBrands();
    return ResponseEntity.ok(modelos);
  }
}
