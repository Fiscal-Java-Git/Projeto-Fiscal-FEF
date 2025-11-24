package com.projetofef.repositories;

import com.projetofef.domains.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
    List<Entidade> findByUsuario_Id(Long usuarioId);
    List<Entidade> findByUsuario_IdAndNomeContainingIgnoreCase(Long usuarioId, String nome);
}
