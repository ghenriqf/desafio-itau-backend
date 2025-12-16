package com.ghenriqf.desafio_itau_backend.controller;


import com.ghenriqf.desafio_itau_backend.dto.TransacaoEstatisticaResponse;
import com.ghenriqf.desafio_itau_backend.dto.TransacaoRequest;
import com.ghenriqf.desafio_itau_backend.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @Operation(
            summary = "Receber Transações",
            description = "Este é o endpoint que irá receber as Transações. Cada transação consiste de um valor e uma dataHora de quando ela aconteceu."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transação aceita e registrada"),
            @ApiResponse(responseCode = "400", description = "Erro de validação do payload"),
            @ApiResponse(responseCode = "422", description = "Transação inválida segundo as regras de negócio")
    })
    @PostMapping("/transacao")
    public ResponseEntity<?> receberTransacao(@RequestBody @Valid @NotNull TransacaoRequest transacaoRequest) {
        transacaoService.salvarTransacao(transacaoRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();

    }

    @Operation(
            summary = "Limpar Transações",
            description = "Este endpoint apaga todos os dados de transações que estejam armazenados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Todas as informações foram apagadas com sucesso"
    )
    @DeleteMapping("/transacao")
    public ResponseEntity<?> limparTransacoes () {
        transacaoService.limparTransacoes();
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(
            summary = "Calcular Estatísticas",
            description = "Este endpoint retorna estatísticas das transações que aconteceram nos últimos 60 segundos."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Estatísticas calculadas e retornadas em JSON."
    )
    @GetMapping("/estatistica")
    public TransacaoEstatisticaResponse calcularEstatisticas () {
        return transacaoService.calcularEstatistica();
    }
}
