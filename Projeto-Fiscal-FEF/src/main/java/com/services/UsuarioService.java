package com.services;

import com.projetofef.domains.Usuario;
import com.projetofef.domains.dtos.UsuarioDTO;
import com.projetofef.mappers.UsuarioMapper;
import com.projetofef.repositories.UsuarioRepository;
import com.projetofef.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioDTO> findAll() {
        return UsuarioMapper.toDtoList(repository.findAll());
    }

    public UsuarioDTO findById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + id));
        return UsuarioMapper.toDto(usuario);
    }

    public UsuarioDTO create(UsuarioDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario = repository.save(usuario);
        return UsuarioMapper.toDto(usuario);
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + id));
        UsuarioMapper.copyToEntity(dto, usuario);
        usuario = repository.save(usuario);
        return UsuarioMapper.toDto(usuario);
    }

    public void delete(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + id));
        repository.delete(usuario);
    }
}
