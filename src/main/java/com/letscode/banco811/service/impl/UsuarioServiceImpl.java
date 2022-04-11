package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.UsuarioRequest;
import com.letscode.banco811.dto.UsuarioResponse;
import com.letscode.banco811.model.Usuario;
import com.letscode.banco811.repository.UsuarioRepository;
import com.letscode.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired UsuarioRepository usuarioRepository;

  @Override
  public UsuarioResponse create(UsuarioRequest usuarioRequest) {

    Usuario usuario = new Usuario(usuarioRequest);
    usuarioRepository.save(usuario);
    return new UsuarioResponse(usuario);
  }

  @Override
  public Page<UsuarioResponse> getAll(String nome, int page, int size) {

    PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");

    if (nome != null) {
      return new PageImpl<>(
          UsuarioResponse.toResponse(usuarioRepository.findByNome(nome, pageRequest).getContent()),
          pageRequest,
          usuarioRepository.count());
    }

    return new PageImpl<>(
        UsuarioResponse.toResponse(usuarioRepository.findAll(pageRequest).getContent()),
        pageRequest,
        usuarioRepository.count());
  }

  @Override
  public Usuario getById(Integer id) {
    return usuarioRepository.findById(id).orElseThrow();
  }

  @Override
  public UsuarioResponse update(UsuarioRequest usuarioRequest, Integer id) {
    Usuario usuario = usuarioRepository.findById(id).orElseThrow();

    usuario.setNome(usuarioRequest.getNome());
    usuario.setCpf(usuarioRequest.getCpf());
    usuario.setSenha(usuarioRequest.getSenha());
    usuarioRepository.save(usuario);

    return new UsuarioResponse(usuario);
  }

  @Override
  public void delete(Integer id) { usuarioRepository.deleteById(id); }
}
