package com.letscode.banco811.repository;

import com.letscode.banco811.model.Conta;
import com.letscode.banco811.model.TipoConta;
import com.letscode.banco811.projection.ContaView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    List<ContaView> findAllByTipoConta(TipoConta tipoConta);

    @Query("select c from Conta c where c.numero = :numero and c.agencia = :agencia")
    Conta findByNumeroAndAgencia(@Param("numero") Integer numero, @Param("agencia") Integer agencia);
}
