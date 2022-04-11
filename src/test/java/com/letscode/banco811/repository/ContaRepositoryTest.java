package com.letscode.banco811.repository;

import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.TipoConta;
import com.letscode.banco811.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContaRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ContaRepository contaRepository;

    @Test
    void validate_findAll_not_empty_if_repository_not_empty() {
        var usuario = new Usuario();
        var conta = new Conta();

        usuario.setCpf("123456789");
        usuario.setNome("Usuario");
        usuario.setSenha("senha");

        conta.setNumero(123);
        conta.setAgencia(123);
        conta.setSaldo(new BigDecimal(100));
        conta.setTipoConta(TipoConta.PJ);
        conta.setUsuario(usuario);

        entityManager.persist(usuario);
        entityManager.persist(conta);

        var contas = contaRepository.findAll();

        assertEquals(1, contas.size());
    }

    @Test
    void validate_findAll_empty_if_repository_empty() {
        var contas = contaRepository.findAll();

        assertEquals(0, contas.size());
    }

    @Test
    void validate_findByNumeroAndAgencia_not_null_if_exists() {
        var usuario = new Usuario();
        var conta = new Conta();

        usuario.setCpf("123456789");
        usuario.setNome("Usuario");
        usuario.setSenha("senha");

        conta.setNumero(123);
        conta.setAgencia(1);
        conta.setSaldo(new BigDecimal(100));
        conta.setTipoConta(TipoConta.PJ);
        conta.setUsuario(usuario);

        entityManager.persist(usuario);
        entityManager.persist(conta);

        var contaFind = contaRepository.findByNumeroAndAgencia(123, 1);

        assertNotNull(contaFind);
    }

    @Test
    void validate_findByNumeroAndAgencia_null_if_not_exists() {
        var contaFind = contaRepository.findByNumeroAndAgencia(123, 1);

        assertNull(contaFind);
    }
}