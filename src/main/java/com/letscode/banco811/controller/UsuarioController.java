package com.letscode.banco811.controller;

import com.letscode.banco811.dto.UsuarioRequest;
import com.letscode.banco811.dto.UsuarioResponse;
import com.letscode.banco811.model.Usuario;
import com.letscode.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired UsuarioService usuarioService;

  @PostMapping
  public UsuarioResponse create(@RequestBody UsuarioRequest usuarioRequest) {
    return usuarioService.create(usuarioRequest);
  }

  @GetMapping
  public Page<UsuarioResponse> getAll(
      @RequestParam(required = false) String nome,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "3") int size) {
    return usuarioService.getAll(nome, page, size);
  }

  @GetMapping("/{id}")
  public UsuarioResponse getById(@PathVariable Integer id) {
    return new UsuarioResponse(usuarioService.getById(id));
  }

  @PutMapping("/{id}")
  public UsuarioResponse update(@PathVariable Integer id, @RequestBody UsuarioRequest usuarioRequest) {
    return usuarioService.update(usuarioRequest, id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) { usuarioService.delete(id); }
}
