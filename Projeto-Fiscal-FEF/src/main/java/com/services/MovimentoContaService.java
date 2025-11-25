package com.services;

import com.projetofef.domains.MovimentoConta;
import com.projetofef.domains.dtos.MovimentoContaDTO;
import com.projetofef.mappers.MovimentoContaMapper;
import com.projetofef.repositories.MovimentoContaRepository;
import com.projetofef.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentoContaService {

    private final MovimentoContaRepository repository;

    public MovimentoContaService(MovimentoContaRepository repository) {
        this.repository = repository;
    }

    public List<MovimentoContaDTO> findAll() {
        return MovimentoContaMapper.toDtoList(repository.findAll());
    }

    public MovimentoContaDTO findById(Long id) {
        MovimentoConta movimento = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Movimento não encontrado: " + id));
        return MovimentoContaMapper.toDto(movimento);
    }
}
