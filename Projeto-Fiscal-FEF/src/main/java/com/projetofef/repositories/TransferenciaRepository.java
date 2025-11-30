package com.projetofef.repositories;

import com.projetofef.domains.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByContaOrigem_IdOrContaDestino_IdAndDataBetween(Long contaOrigemId, Long contaDestinoId, LocalDate inicio, LocalDate fim);
}
