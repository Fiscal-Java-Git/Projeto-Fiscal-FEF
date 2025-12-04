package com.services;

import com.domains.ContaBancaria;
import com.domains.Transferencia;
import com.domains.Usuario;
import com.domains.dtos.TransferenciaDTO;
import com.mappers.TransferenciaMapper;
import com.repositories.ContaBancariaRepository;
import com.repositories.TransferenciaRepository;
import com.repositories.UsuarioRepository;
import com.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ContaBancariaRepository contaRepository;

    public TransferenciaService(TransferenciaRepository repository,
                                UsuarioRepository usuarioRepository,
                                ContaBancariaRepository contaRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.contaRepository = contaRepository;
    }

    public List<TransferenciaDTO> findAll() {
        return TransferenciaMapper.toDtoList(repository.findAll());
    }

    public TransferenciaDTO findById(Long id) {
        Transferencia transferencia = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Transferência não encontrada: " + id));
        return TransferenciaMapper.toDto(transferencia);
    }

    @Transactional
    public TransferenciaDTO create(TransferenciaDTO dto) {
        Transferencia t = toEntityWithRelations(dto);
        t = repository.save(t);
        return TransferenciaMapper.toDto(t);
    }

    @Transactional
    public TransferenciaDTO update(Long id, TransferenciaDTO dto) {
        Transferencia existing = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Transferência não encontrada: " + id));

        existing.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId())));
        existing.setContaOrigem(contaRepository.findById(dto.getContaOrigemId())
                .orElseThrow(() -> new DataIntegrityException("Conta de origem não encontrada: " + dto.getContaOrigemId())));
        existing.setContaDestino(contaRepository.findById(dto.getContaDestinoId())
                .orElseThrow(() -> new DataIntegrityException("Conta de destino não encontrada: " + dto.getContaDestinoId())));
        existing.setData(dto.getData());
        existing.setValor(dto.getValor());
        existing.setObservacao(dto.getObservacao());

        existing = repository.save(existing);
        return TransferenciaMapper.toDto(existing);
    }

    @Transactional
    public void delete(Long id) {
        Transferencia t = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Transferência não encontrada: " + id));
        repository.delete(t);
    }

    private Transferencia toEntityWithRelations(TransferenciaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new DataIntegrityException("Usuário não encontrado: " + dto.getUsuarioId()));

        ContaBancaria origem = contaRepository.findById(dto.getContaOrigemId())
                .orElseThrow(() -> new DataIntegrityException("Conta de origem não encontrada: " + dto.getContaOrigemId()));

        ContaBancaria destino = contaRepository.findById(dto.getContaDestinoId())
                .orElseThrow(() -> new DataIntegrityException("Conta de destino não encontrada: " + dto.getContaDestinoId()));

        Transferencia t = new Transferencia();
        t.setUsuario(usuario);
        t.setContaOrigem(origem);
        t.setContaDestino(destino);
        t.setData(dto.getData());
        t.setValor(dto.getValor());
        t.setObservacao(dto.getObservacao());

        return t;
    }
}
