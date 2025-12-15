package com.ghenriqf.desafio_itau_backend.repository;

import com.ghenriqf.desafio_itau_backend.dto.TransacaoRequest;
import com.ghenriqf.desafio_itau_backend.dto.TransacaoEstatisticaResponse;
import com.ghenriqf.desafio_itau_backend.mapper.TransacaoMapper;
import com.ghenriqf.desafio_itau_backend.model.Transacao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TransacaoRepository {
    private final ArrayList<Transacao> transacaos = new ArrayList<>();
    private final TransacaoMapper transacaoMapper;

    public TransacaoRepository( TransacaoMapper transacaoMapper) {
        this.transacaoMapper = transacaoMapper;
    }

    public void salvarTransacao (TransacaoRequest transacaoRequest) {
        transacaos.add(transacaoMapper.toModel(transacaoRequest));
    }

    public void limparTransacoes () {
        transacaos.clear();
    }

    public ArrayList<Transacao> obterTransacoes () {
        return transacaos;
    }

}