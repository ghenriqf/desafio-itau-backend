package com.ghenriqf.desafio_itau_backend.mapper;

import com.ghenriqf.desafio_itau_backend.dto.TransacaoRequest;
import com.ghenriqf.desafio_itau_backend.model.Transacao;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {

    public Transacao toModel (TransacaoRequest transacaoRequest) {
        return new Transacao(
                transacaoRequest.valor(),
                transacaoRequest.dataHora()
        );
    }
}
