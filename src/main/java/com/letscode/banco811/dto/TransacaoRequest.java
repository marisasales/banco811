package com.letscode.banco811.dto;

import com.letscode.banco811.model.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class TransacaoRequest {

    private BigDecimal valor;
    private TipoTransacao tipoTransacao;
    private Integer numero;
    private Integer agencia;
}
