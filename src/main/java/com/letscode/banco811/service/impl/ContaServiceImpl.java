package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.ContaRequest;
import com.letscode.banco811.dto.ContaResponse;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.TipoConta;
import com.letscode.banco811.projection.ContaView;
import com.letscode.banco811.repository.ContaRepository;
import com.letscode.banco811.service.ContaService;
import com.letscode.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    UsuarioService usuarioService;

    @Override
    public ContaResponse create(Integer usuarioId, ContaRequest contaRequest) {
        var usuario = usuarioService.getById(usuarioId);
        Conta conta = new Conta(contaRequest);
        conta.setUsuario(usuario);
        contaRepository.save(conta);

        return new ContaResponse(conta);
    }

    @Override
    public Page<ContaResponse> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "agencia");

        return new PageImpl<>(
            ContaResponse.toResponse(contaRepository.findAll(pageRequest).getContent()),
            pageRequest,
            contaRepository.count());
    }

    @Override
    public Conta getById(Integer id) {
        return contaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ContaView> getAllViewByTipoConta(TipoConta tipoConta) {
        return contaRepository.findAllByTipoConta(tipoConta);
    }

    @Override
    public ContaResponse update(ContaRequest contaRequest, Integer id) {
        var conta = contaRepository.findById(id).orElseThrow();

        conta.setNumero(contaRequest.getNumero());
        conta.setAgencia(contaRequest.getAgencia());
        conta.setSaldo(contaRequest.getSaldo());
        conta.setTipoConta(contaRequest.getTipoConta());

        return new ContaResponse(contaRepository.save(conta));
    }

    @Override
    public void delete(Integer id) { contaRepository.deleteById(id); }

    @Override
    public Conta getByNumeroAndAgencia(Integer numero, Integer agencia) {
        return contaRepository.findByNumeroAndAgencia(numero, agencia);
    }

    @Override
    public void updateSaldo(Conta conta, BigDecimal valor, Boolean origem) {
        if (origem) {
            conta.setSaldo(conta.getSaldo().subtract(valor));
        } else {
            conta.setSaldo(conta.getSaldo().add(valor));
        }

        contaRepository.save(conta);
    }
}
