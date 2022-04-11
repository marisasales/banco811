package com.letscode.banco811.projection;

import com.letscode.banco811.model.TipoTransacao;

import java.math.BigDecimal;

public interface TransacaoView {

    BigDecimal getValor();

    TipoTransacao getTipoTransacao();

    ContaView getConta();
}
