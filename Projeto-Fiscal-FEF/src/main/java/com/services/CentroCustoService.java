package com.services;

import com.projetofef.domains.CentroCusto;
import com.projetofef.domains.Usuario;
import com.projetofef.domains.dtos.CentroCustoDTO;
import com.projetofef.mappers.CentroCustoMapper;
import com.projetofef.repositories.CentroCustoRepository;
import com.projetofef.repositories.UsuarioRepository;
import com.projetofef.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CentroCustoService {

    private final CentroCustoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public CentroCustoService(CentroCustoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<CentroCustoDTO> findAll() {
        return CentroCustoMapper.toDtoList(repository.findAll());
    }

    public CentroCustoDTO findById(Long id) {
        CentroCusto centro = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Centro de Custo não encontrado: " + id));
        return CentroCustoMapper.toDto(centro);
    }

    @Transactional
    public CentroCustoDTO create(CentroCustoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));
        CentroCusto centro = CentroCustoMapper.toEntity(dto, usuario);
        centro = repository.save(centro);
        return CentroCustoMapper.toDto(centro);
    }

    @Transactional
    public CentroCustoDTO update(Long id, CentroCustoDTO dto) {
        CentroCusto centro = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Centro de Custo não encontrado: " + id));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));
        CentroCustoMapper.copyToEntity(dto, centro, usuario);
        centro = repository.save(centro);
        return CentroCustoMapper.toDto(centro);
    }

    @Transactional
    public void delete(Long id) {
        CentroCusto centro = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Centro de Custo não encontrado: " + id));
        centro.setAtivo(false);
        repository.save(centro);
    }
}
