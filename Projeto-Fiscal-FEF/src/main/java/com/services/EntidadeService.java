package com.services;

import com.projetofef.domains.Entidade;
import com.projetofef.domains.Usuario;
import com.projetofef.domains.dtos.EntidadeDTO;
import com.projetofef.mappers.EntidadeMapper;
import com.projetofef.repositories.EntidadeRepository;
import com.projetofef.repositories.UsuarioRepository;
import com.projetofef.services.exceptions.DataIntegrityException;
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

    @Transactional
    public EntidadeDTO create(EntidadeDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));

        Entidade entidade = EntidadeMapper.toEntity(dto, usuario); // passa o usuario
        entidade = repository.save(entidade);
        return EntidadeMapper.toDto(entidade);
    }

    @Transactional
    public EntidadeDTO update(Long id, EntidadeDTO dto) {
        Entidade entidade = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Entidade não encontrada: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));

        EntidadeMapper.copyToEntity(dto, entidade, usuario); // passa o usuario
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
