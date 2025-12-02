package com.sciense.fipe_orchestrator.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sciense.fipe_orchestrator.application.service.InitialLoadService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/load")
public class InitialLoadController {

    private final InitialLoadService initialLoadService;

    public InitialLoadController(InitialLoadService initialLoadService) {
        this.initialLoadService = initialLoadService;
    }

    @PostMapping("/initial")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> initialLoad() {
        initialLoadService.executeInitialLoad().subscribe();;
        return ResponseEntity.ok("Carga inicial disparada com sucesso!");
    }
}

