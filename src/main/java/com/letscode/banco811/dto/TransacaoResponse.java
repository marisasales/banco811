package com.letscode.banco811.dto;

import com.letscode.banco811.model.TipoTransacao;
import com.letscode.banco811.model.Transacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class TransacaoResponse {

    private BigDecimal valor;
    private TipoTransacao tipoTransacao;
    private Integer numero;
    private Integer agencia;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public TransacaoResponse(Transacao transacao) {
        this.valor = transacao.getValor();
        this.tipoTransacao = transacao.getTipoTransacao();
        this.numero = transacao.getNumero();
        this.agencia = transacao.getAgencia();
        this.dataCriacao = transacao.getDataCriacao();
        this.dataAtualizacao = transacao.getDataAtualizacao();
    }

    public static List<TransacaoResponse> toResponse(List<Transacao> transacoes) {
        return transacoes.stream().map(TransacaoResponse::new).collect(Collectors.toList());
    }
}
