package com.sciense.fipe_orchestrator.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MODELS")
public class Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "codigo_fipe", nullable = false)
  private String codigoFipe;

  @Column(nullable = false)
  private String nome;

  @ManyToOne
  @JoinColumn(name = "brand_id", nullable = false)
  private Brand brand;

  public Long getId() {
      return id;
  }

  public String getCodigoFipe() {
      return codigoFipe;
  }

  public void setCodigoFipe(String codigoFipe) {
      this.codigoFipe = codigoFipe;
  }

  public String getNome() {
      return nome;
  }

  public void setNome(String nome) {
      this.nome = nome;
  }

  public Brand getBrand() {
      return brand;
  }

  public void setBrand(Brand brand) {
      this.brand = brand;
  }
}
