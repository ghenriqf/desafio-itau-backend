package com.ghenriqf.desafio_itau_backend.service;

import com.ghenriqf.desafio_itau_backend.dto.TransacaoDTO;
import com.ghenriqf.desafio_itau_backend.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void salvarTransacao (TransacaoDTO transacaoDTO) {
        Instant dataHoraAgora = Instant.now();
        Instant dataHoraTransacao = transacaoDTO.dataHora().toInstant();

        if (dataHoraTransacao.isAfter(dataHoraAgora)) {
            throw new IllegalArgumentException("Data invalida");
        }
        transacaoRepository.salvarTransacao(transacaoDTO);
    }
}
