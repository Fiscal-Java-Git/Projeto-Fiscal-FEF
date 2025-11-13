package com.projetofef.repositories;

import com.projetofef.domains.FaturaCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository
public interface FaturaCartaoRepository extends JpaRepository<FaturaCartao, Long> {
    List<FaturaCartao> findByCartao_Id(Long cartaoId);
    Optional<FaturaCartao> findByCartao_IdAndCompetencia(Long cartaoId, LocalDate competencia);
}
