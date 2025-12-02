package com.sciense.fipe_orchestrator.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciense.fipe_orchestrator.application.dto.LoginRequestDTO;
import com.sciense.fipe_orchestrator.application.dto.LoginResponseDTO;
import com.sciense.fipe_orchestrator.application.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }
  
  @PostMapping
  public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
      return ResponseEntity.ok(authService.login(dto));
  }
}
