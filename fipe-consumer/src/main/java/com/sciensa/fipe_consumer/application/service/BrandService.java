package com.sciensa.fipe_consumer.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sciensa.fipe_consumer.application.dto.BrandResponse;
import com.sciensa.fipe_consumer.application.repository.BrandRepository;

@Service
public class BrandService {
  private final BrandRepository brandRepository;

  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public List<BrandResponse> getAllBrands() {
    return brandRepository.findAll().stream()
        .map(brand -> new BrandResponse(brand.getCode(), brand.getName()))
        .toList();
  }
}
