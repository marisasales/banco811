package com.letscode.banco811.service;

import com.letscode.banco811.dto.ContaRequest;
import com.letscode.banco811.dto.ContaResponse;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.TipoConta;
import com.letscode.banco811.projection.ContaView;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface ContaService {

    ContaResponse create(Integer usuarioId, ContaRequest contaRequest);

    Page<ContaResponse> getAll(int page, int size);

    Conta getById(Integer id);

    List<ContaView> getAllViewByTipoConta(TipoConta tipoConta);

    ContaResponse update(ContaRequest contaRequest, Integer id);

    void delete(Integer id);

    Conta getByNumeroAndAgencia(Integer numero, Integer agencia);

    void updateSaldo(Conta conta, BigDecimal valor, Boolean isOrigem);
}
