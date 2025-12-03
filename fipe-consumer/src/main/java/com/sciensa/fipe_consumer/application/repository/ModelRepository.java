package com.sciensa.fipe_consumer.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sciensa.fipe_consumer.application.model.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
  @Query("""
       SELECT m
       FROM Model m
       WHERE LOWER(m.brand.name) = LOWER(:value)
          OR LOWER(m.brand.code) = LOWER(:value)
       """)
  List<Model> searchByBrandNameOrCode(@Param("value") String value);

  Optional<Model> findByCode(String code);
}
