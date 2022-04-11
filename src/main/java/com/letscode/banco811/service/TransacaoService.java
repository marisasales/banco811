package com.letscode.banco811.service;

import com.letscode.banco811.dto.TransacaoRequest;
import com.letscode.banco811.dto.TransacaoResponse;
import com.letscode.banco811.model.TipoTransacao;
import com.letscode.banco811.projection.TransacaoView;

import java.util.List;

public interface TransacaoService {

    TransacaoResponse create(Integer id, TransacaoRequest transacaoRequest);

    List<TransacaoResponse> getAll();

    List<TransacaoView> getAllViewByTipoTransacao(TipoTransacao tipoTransacao);

    void delete(Integer id);
}
