package com.ghenriqf.desafio_itau_backend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoEstatistica {
    // Quantidade de transações nos últimos 60 segundos
    Long count;

    // Soma total do valor transacionado nos últimos 60 segundos
    Double sum;

    // Média do valor transacionado nos últimos 60 segundos
    Double avg;

    // Menor valor transacionado nos últimos 60 segundos
    Double min;

    // Maior valor transacionado nos últimos 60 segundos
    Double max;
}
