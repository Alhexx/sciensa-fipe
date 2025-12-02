package com.sciense.fipe_orchestrator.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sciense.fipe_orchestrator.application.model.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{
  
}
