package com.ghenriqf.desafio_itau_backend.controller;


import com.ghenriqf.desafio_itau_backend.dto.TransacaoEstatisticaResponse;
import com.ghenriqf.desafio_itau_backend.dto.TransacaoRequest;
import com.ghenriqf.desafio_itau_backend.service.TransacaoService;
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

    @PostMapping("/transacao")
    public ResponseEntity<?> receberTransacao (@RequestBody @Valid @NotNull TransacaoRequest transacaoRequest) {
        try{
            transacaoService.salvarTransacao(transacaoRequest);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(422)
                    .build();
        }
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<?> limparTransacoes () {
        transacaoService.limparTransacoes();
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/estatistica")
    public TransacaoEstatisticaResponse calcularEstatisticas () {
        return transacaoService.calcularEstatistica();
    }
}
