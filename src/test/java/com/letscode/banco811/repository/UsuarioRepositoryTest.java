package com.letscode.banco811.repository;

import com.letscode.banco811.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void validate_findAll_empty_if_repository_empty() {
        var usuarios = usuarioRepository.findAll();
        assertEquals(0, usuarios.size());
    }

    @Test
    public void validate_findAll_not_empty_if_repository_not_empty() {
        var usuario = new Usuario();
        usuario.setCpf("12345678901");
        usuario.setNome("Test");
        usuario.setSenha("123456");

        entityManager.persist(usuario);
        var usuarios = usuarioRepository.findAll();

        assertEquals(1, usuarios.size());
    }

    @Test
    public void validate_findByNome_empty_if_repository_empty() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        var usuarios = usuarioRepository.findByNome("Test", pageRequest);
        assertEquals(0, usuarios.getTotalElements());
    }

    @Test
    public void validate_findByNome_not_empty_if_repository_not_empty() {
        var usuario = new Usuario();
        PageRequest pageRequest = PageRequest.of(0, 3);

        usuario.setCpf("12345678901");
        usuario.setNome("Test");
        usuario.setSenha("123456");

        entityManager.persist(usuario);

        var usuarios = usuarioRepository.findByNome("Test", pageRequest);
        assertEquals(1, usuarios.getTotalElements());
        assertEquals(usuario, usuarios.stream().findFirst().get());
    }

    @Test
    public void validate_updateNome_success() {
        var usuario = new Usuario();
        usuario.setCpf("12345678901");
        usuario.setNome("Test");
        usuario.setSenha("123456");

        usuario = entityManager.persist(usuario);

        usuario.setNome("Test2");
        usuarioRepository.save(usuario);

        var usuarioFound = usuarioRepository.findById(usuario.getId()).get();

        assertEquals(usuario.getId(), usuarioFound.getId());
        assertEquals("Test2", usuarioFound.getNome());
    }
}