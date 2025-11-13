package com.projetofef.repositories;

import com.projetofef.domains.Lancamento;
import com.projetofef.domains.enums.TipoLancamento;
import com.projetofef.domains.enums.StatusLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    List<Lancamento> findByUsuario_Id(Long usuarioId);
    List<Lancamento> findByUsuario_IdAndTipoAndStatusAndDataVencimentoBetween(
            Long usuarioId, TipoLancamento tipo, StatusLancamento status, LocalDate inicio, LocalDate fim);
}
