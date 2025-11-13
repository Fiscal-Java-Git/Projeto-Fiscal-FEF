package com.projetofef.repositories;

import com.projetofef.domains.MovimentoConta;
import com.projetofef.domains.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimentoContaRepository extends JpaRepository<MovimentoConta, Long> {
    List<MovimentoConta> findByConta_IdAndDataMovimentoBetween(Long contaId, LocalDate inicio, LocalDate fim);
    List<MovimentoConta> findByConta_IdAndTipo(Long contaId, TipoTransacao tipo);
}
