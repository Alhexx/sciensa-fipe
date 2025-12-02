package com.sciensa.fipe_consumer.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sciensa.fipe_consumer.application.model.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
  
}
