package com.ghenriqf.desafio_itau_backend.controller;


import com.ghenriqf.desafio_itau_backend.dto.TransacaoDTO;
import com.ghenriqf.desafio_itau_backend.service.TransacaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/transacao")
    public ResponseEntity<?> receberTransacao (@RequestBody @Valid @NotNull TransacaoDTO transacaoDTO) {
        try{
            transacaoService.salvarTransacao(transacaoDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Transação salva com sucesso!");
        } catch (Exception e) {
            return ResponseEntity
                    .status(422)
                    .body(e.getMessage());
        }
    }
}
