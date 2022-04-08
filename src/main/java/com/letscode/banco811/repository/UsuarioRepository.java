package com.letscode.banco811.repository;

import com.letscode.banco811.dto.UsuarioResponse;
import com.letscode.banco811.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Page<UsuarioResponse> findByNome(String nome, Pageable pageable);
}
