package com.ghenriqf.desafio_itau_backend.mapper;

import com.ghenriqf.desafio_itau_backend.dto.TransacaoDTO;
import com.ghenriqf.desafio_itau_backend.model.Transacao;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {

    public Transacao toModel (TransacaoDTO transacaoDTO) {
        return new Transacao(
                transacaoDTO.valor(),
                transacaoDTO.dataHora()
        );
    }
}
