package com.letscode.banco811.repository;

import com.letscode.banco811.model.TipoTransacao;
import com.letscode.banco811.model.Transacao;
import com.letscode.banco811.projection.TransacaoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    List<TransacaoView> findAllByTipoTransacao(TipoTransacao tipoTransacao);
}
