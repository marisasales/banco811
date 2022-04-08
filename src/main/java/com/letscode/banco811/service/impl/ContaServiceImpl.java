package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.ContaRequest;
import com.letscode.banco811.dto.ContaResponse;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.repository.ContaRepository;
import com.letscode.banco811.service.ContaService;
import com.letscode.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ContaServiceImpl implements ContaService {

  @Autowired ContaRepository contaRepository;

  @Autowired UsuarioService usuarioService;

  @Override
  public ContaResponse create(Integer usuarioId, ContaRequest contaRequest) {
    var usuario = usuarioService.getById(usuarioId);
    Conta conta = new Conta();

    conta.setNumero(contaRequest.getNumero());
    conta.setAgencia(contaRequest.getAgencia());
    conta.setSaldo(contaRequest.getSaldo());
    conta.setTipoConta(contaRequest.getTipoConta());
    conta.setUsuario(usuario);

    conta = contaRepository.save(conta);

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
  public ContaResponse getById(Integer id) {
    var conta = contaRepository.findById(id).orElseThrow();
    return new ContaResponse(conta);
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
  public void delete(Integer id) {
    var conta = contaRepository.findById(id).orElseThrow();
    contaRepository.delete(conta);
  }
}
