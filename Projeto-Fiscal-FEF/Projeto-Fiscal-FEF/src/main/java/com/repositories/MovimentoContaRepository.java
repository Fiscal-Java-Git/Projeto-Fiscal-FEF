package com.repositories;

import com.domains.MovimentoConta;
import com.domains.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentoContaRepository extends JpaRepository<MovimentoConta, Long> {
    List<MovimentoConta> findByConta_IdOrderByDataMovimentoAsc(Long contaId);
    List<MovimentoConta> findByConta_IdAndDataMovimentoBetween(Long contaId, LocalDateTime inicio, LocalDateTime fim);
    List<MovimentoConta> findByConta_IdAndTipo(Long contaId, TipoTransacao tipo);
}
