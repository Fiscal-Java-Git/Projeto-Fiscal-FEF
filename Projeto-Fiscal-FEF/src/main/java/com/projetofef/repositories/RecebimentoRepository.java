package com.projetofef.repositories;

import com.projetofef.domains.Recebimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecebimentoRepository extends JpaRepository<Recebimento, Long> {
    List<Recebimento> findByLancamento_Id(Long lancamentoId);
}
