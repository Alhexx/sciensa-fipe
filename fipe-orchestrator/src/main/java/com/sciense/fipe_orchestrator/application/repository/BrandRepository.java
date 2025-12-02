package com.sciense.fipe_orchestrator.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sciense.fipe_orchestrator.application.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
  
}
