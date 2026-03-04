package com.repositories;

import com.domains.Lancamento;
import com.domains.enums.TipoLancamento;
import com.domains.enums.StatusLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    List<Lancamento> findByUsuario_Id(Long usuarioId);

    List<Lancamento> findByUsuario_IdAndTipoAndStatusAndDataVencimentoBetween(
            Long usuarioId,
            TipoLancamento tipo,
            StatusLancamento status,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    List<Lancamento> findByUsuario_IdAndTipoAndStatus(
            Long usuarioId,
            TipoLancamento tipo,
            StatusLancamento status
    );


    Page<Lancamento> findByUsuario_Id(Long usuarioId, Pageable pageable);


    @Query("""
       SELECT COALESCE(SUM(l.valor), 0)
       FROM Lancamento l
       WHERE l.usuario.id = :usuarioId
       AND l.tipo = :tipo
       """)
    BigDecimal somarPorTipo(
            @Param("usuarioId") Long usuarioId,
            @Param("tipo") TipoLancamento tipo
    );
}