package com.letscode.banco811.service;

import com.letscode.banco811.dto.UsuarioRequest;
import com.letscode.banco811.dto.UsuarioResponse;
import com.letscode.banco811.model.Usuario;
import org.springframework.data.domain.Page;

public interface UsuarioService {

    Page<UsuarioResponse> getAll(String name, int page, int size);

    UsuarioResponse create(UsuarioRequest usuarioRequest);

    Usuario getById(Integer id);

    UsuarioResponse update(UsuarioRequest usuarioRequest, Integer id);

    void delete(Integer id);
}
