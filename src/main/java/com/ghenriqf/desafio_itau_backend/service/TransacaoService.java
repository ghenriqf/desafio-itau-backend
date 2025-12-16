package com.ghenriqf.desafio_itau_backend.service;

import com.ghenriqf.desafio_itau_backend.dto.TransacaoEstatisticaResponse;
import com.ghenriqf.desafio_itau_backend.dto.TransacaoRequest;
import com.ghenriqf.desafio_itau_backend.exception.TransacaoInvalidaException;
import com.ghenriqf.desafio_itau_backend.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void salvarTransacao (TransacaoRequest transacaoRequest) {
        Instant dataHoraAgora = Instant.now();
        Instant dataHoraTransacao = transacaoRequest.dataHora().toInstant();

        if (dataHoraTransacao.isAfter(dataHoraAgora)) {
            throw new TransacaoInvalidaException("Data inválida.");
        }
        transacaoRepository.salvarTransacao(transacaoRequest);
    }

    public void limparTransacoes () {
        transacaoRepository.limparTransacoes();
    }

    public TransacaoEstatisticaResponse calcularEstatistica () {
        OffsetDateTime horaInicial = OffsetDateTime.now().minusSeconds(60);

        DoubleSummaryStatistics stats = transacaoRepository.obterTransacoes().stream()
                .filter(t -> !t.getDataHora().isBefore(horaInicial))
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();

        if (stats.getCount() == 0) {
            return new TransacaoEstatisticaResponse(0L, 0.0, 0.0, 0.0, 0.0);
        }
        return new TransacaoEstatisticaResponse(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );
    }
}