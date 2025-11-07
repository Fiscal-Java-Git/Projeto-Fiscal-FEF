package com.services;

import com.projetofef.domains.ContaBancaria;
import com.projetofef.domains.Usuario;
import com.projetofef.domains.dtos.ContaBancariaDTO;
import com.projetofef.mappers.ContaBancariaMapper;
import com.projetofef.repositories.ContaBancariaRepository;
import com.projetofef.repositories.UsuarioRepository;
import com.projetofef.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaBancariaService {

    private final ContaBancariaRepository repository;
    private final UsuarioRepository usuarioRepository;

    public ContaBancariaService(ContaBancariaRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ContaBancariaDTO> findAll() {
        return ContaBancariaMapper.toDtoList(repository.findAll());
    }

    public ContaBancariaDTO findById(Long id) {
        ContaBancaria conta = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Conta não encontrada: " + id));
        return ContaBancariaMapper.toDto(conta);
    }

    @Transactional
    public ContaBancariaDTO create(ContaBancariaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));
        ContaBancaria conta = ContaBancariaMapper.toEntity(dto, usuario);
        conta = repository.save(conta);
        return ContaBancariaMapper.toDto(conta);
    }

    @Transactional
    public ContaBancariaDTO update(Long id, ContaBancariaDTO dto) {
        ContaBancaria conta = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Conta não encontrada: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));

        ContaBancariaMapper.copyToEntity(dto, conta, usuario);
        conta = repository.save(conta);
        return ContaBancariaMapper.toDto(conta);
    }

    @Transactional
    public void delete(Long id) {
        ContaBancaria conta = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Conta não encontrada: " + id));
        conta.setAtiva(false);
        repository.save(conta);
    }
}
