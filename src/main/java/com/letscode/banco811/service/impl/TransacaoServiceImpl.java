package com.letscode.banco811.service.impl;

import com.letscode.banco811.dto.TransacaoRequest;
import com.letscode.banco811.dto.TransacaoResponse;
import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.TipoTransacao;
import com.letscode.banco811.model.Transacao;
import com.letscode.banco811.projection.TransacaoView;
import com.letscode.banco811.repository.TransacaoRepository;
import com.letscode.banco811.service.ContaService;
import com.letscode.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ContaService contaService;

    @Override
    public TransacaoResponse create(Integer id, TransacaoRequest transacaoRequest) {
        Conta contaOrigem = contaService.getById(id);
        Conta contaDestino = contaService.getByNumeroAndAgencia(
            transacaoRequest.getNumero(), transacaoRequest.getAgencia());

        Transacao transacao = new Transacao(transacaoRequest);
        transacao.setConta(contaOrigem);

        contaService.updateSaldo(contaOrigem, transacaoRequest.getValor(), true);
        contaService.updateSaldo(contaDestino, transacaoRequest.getValor(), false);

        return new TransacaoResponse(transacaoRepository.save(transacao));
    }

    @Override
    public List<TransacaoResponse> getAll() {
        return TransacaoResponse.toResponse(transacaoRepository.findAll());
    }

    @Override
    public List<TransacaoView> getAllViewByTipoTransacao(TipoTransacao tipoTransacao) {
        return transacaoRepository.findAllByTipoTransacao(tipoTransacao);
    }

    @Override
    public void delete(Integer id) {
        transacaoRepository.deleteById(id);
    }
}
