package com.letscode.banco811.service;

import com.letscode.banco811.dto.ContaRequest;
import com.letscode.banco811.dto.ContaResponse;
import com.letscode.banco811.dto.UsuarioRequest;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.TipoConta;
import com.letscode.banco811.model.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContaService {

    ContaResponse create(Integer usuarioId, ContaRequest contaRequest);

    Page<ContaResponse> getAll(int page, int size);

    ContaResponse getById(Integer id);

    ContaResponse update(ContaRequest contaRequest, Integer id);

    void delete(Integer id);
}
