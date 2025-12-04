package com.services;

import com.domains.MovimentoConta;
import com.domains.dtos.MovimentoContaDTO;
import com.mappers.MovimentoContaMapper;
import com.repositories.MovimentoContaRepository;
import com.services.exceptions.DataIntegrityException;
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
