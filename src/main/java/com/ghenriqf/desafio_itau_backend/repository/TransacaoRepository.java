package com.ghenriqf.desafio_itau_backend.repository;

import com.ghenriqf.desafio_itau_backend.dto.TransacaoDTO;
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

    public void salvarTransacao (TransacaoDTO transacaoDTO) {
        transacaos.add(transacaoMapper.toModel(transacaoDTO));
    }
}
