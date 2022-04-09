package com.letscode.banco811.controller;

import com.letscode.banco811.dto.ContaRequest;
import com.letscode.banco811.dto.ContaResponse;
import com.letscode.banco811.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @PostMapping("/{id}")
    public ContaResponse create(@PathVariable("id") Integer id, @RequestBody ContaRequest contaRequest) {
        return contaService.create(id, contaRequest);
    }

    @GetMapping
    public Page<ContaResponse> getAll(
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "3") int size) {
        return contaService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public ContaResponse getById(@PathVariable Integer id) {
        return contaService.getById(id);
    }

    @PutMapping("/{id}")
    public ContaResponse update(@PathVariable Integer id, @RequestBody ContaRequest contaRequest) {
        return contaService.update(contaRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { contaService.delete(id); }
}
