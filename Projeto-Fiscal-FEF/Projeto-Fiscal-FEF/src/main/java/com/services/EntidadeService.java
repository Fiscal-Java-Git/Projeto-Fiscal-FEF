package com.services;

import com.domains.Entidade;
import com.domains.Usuario;
import com.domains.dtos.EntidadeDTO;
import com.mappers.EntidadeMapper;
import com.repositories.EntidadeRepository;
import com.repositories.UsuarioRepository;
import com.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntidadeService {

    private final EntidadeRepository repository;
    private final UsuarioRepository usuarioRepository;

    public EntidadeService(EntidadeRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<EntidadeDTO> findAll() {
        return EntidadeMapper.toDtoList(repository.findAll());
    }

    public EntidadeDTO findById(Long id) {
        Entidade entidade = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Entidade não encontrada: " + id));
        return EntidadeMapper.toDto(entidade);
    }
    public List<EntidadeDTO> findByNome(String nome) {
        return EntidadeMapper.toDtoList(
                repository.findByNomeContainingIgnoreCase(nome)
        );
    }
    @Transactional
    public EntidadeDTO create(EntidadeDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));

        Entidade entidade = EntidadeMapper.toEntity(dto, usuario);
        entidade = repository.save(entidade);
        return EntidadeMapper.toDto(entidade);
    }

    @Transactional
    public EntidadeDTO update(Long id, EntidadeDTO dto) {
        Entidade entidade = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Entidade não encontrada: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));

        EntidadeMapper.copyToEntity(dto, entidade, usuario);
        entidade = repository.save(entidade);
        return EntidadeMapper.toDto(entidade);
    }

    @Transactional
    public void delete(Long id) {
        Entidade entidade = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Entidade não encontrada: " + id));
        entidade.setAtivo(false);
        repository.save(entidade);
    }
}
