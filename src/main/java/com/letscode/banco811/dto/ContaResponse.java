package com.letscode.banco811.dto;

import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.TipoConta;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class ContaResponse {

    private Integer numero;
    private Integer agencia;
    private BigDecimal saldo;
    private TipoConta tipoConta;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public ContaResponse(Conta conta) {
        this.numero = conta.getNumero();
        this.agencia = conta.getAgencia();
        this.saldo = conta.getSaldo();
        this.tipoConta = conta.getTipoConta();
        this.dataCriacao = conta.getDataCriacao();
        this.dataAtualizacao = conta.getDataAtualizacao();
    }

    public static List<ContaResponse> toResponse(List<Conta> contas) {
        return contas.stream().map(ContaResponse::new).collect(Collectors.toList());
    }
}
