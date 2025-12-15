package com.ghenriqf.desafio_itau_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.OffsetDateTime;

public record TransacaoDTO(
        @NotNull @PositiveOrZero Double valor,
        @NotNull OffsetDateTime dataHora
) {
}
