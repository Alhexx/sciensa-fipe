package com.sciensa.fipe_consumer.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sciensa.fipe_consumer.application.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
  Optional<Brand> findByCode(String code);
}
