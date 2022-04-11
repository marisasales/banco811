package com.letscode.banco811.projection;

import com.letscode.banco811.model.TipoTransacao;

import java.math.BigDecimal;

public interface TransacaoView {

    BigDecimal getValor();

    TipoTransacao getTipoTransacao();

    // TODO:
    //  Preciso ver se os dados são de quem envia ou de quem recebe
    //  Além de deixar isso claro no banco de dados
    //  Pois se for de quem recebe, não devo exibir informações da conta
    ContaView getConta();
}
