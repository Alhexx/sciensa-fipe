package com.sciensa.fipe_consumer.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciensa.fipe_consumer.application.dto.BrandResponse;
import com.sciensa.fipe_consumer.application.service.BrandService;


@RestController
@RequestMapping("/brands")
public class BrandController {
  private final BrandService brandService;

  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping
  public ResponseEntity<List<BrandResponse>> getAllBrands() {
      return ResponseEntity.ok(brandService.getAllBrands());
  }
  
}
