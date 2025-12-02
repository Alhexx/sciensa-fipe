package com.sciensa.fipe_consumer.application.dto;

import java.util.List;

public record FipeModelsResponse(List<ModelResponse> modelos, List<YearResponse> anos) {}
