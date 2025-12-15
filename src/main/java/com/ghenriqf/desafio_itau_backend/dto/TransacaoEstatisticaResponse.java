package com.ghenriqf.desafio_itau_backend.dto;

public record TransacaoEstatisticaResponse(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max
) {
}
