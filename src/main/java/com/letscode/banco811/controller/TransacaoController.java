package com.letscode.banco811.controller;

import com.letscode.banco811.dto.TransacaoRequest;
import com.letscode.banco811.dto.TransacaoResponse;
import com.letscode.banco811.model.TipoTransacao;
import com.letscode.banco811.projection.TransacaoView;
import com.letscode.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping("/{id}")
    public TransacaoResponse create(@PathVariable Integer id, @RequestBody TransacaoRequest transacaoRequest) {
        return transacaoService.create(id, transacaoRequest);
    }

    @GetMapping
    public List<TransacaoResponse> getAll() {
        return transacaoService.getAll();
    }

    @GetMapping("/view")
    public List<TransacaoView> getAllViewByTipoTransacao(@RequestParam TipoTransacao tipoTransacao) {
        return transacaoService.getAllViewByTipoTransacao(tipoTransacao);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        transacaoService.delete(id);
    }
}
