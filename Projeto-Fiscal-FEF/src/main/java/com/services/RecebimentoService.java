package com.services;

import com.projetofef.domains.ContaBancaria;
import com.projetofef.domains.Lancamento;
import com.projetofef.domains.Recebimento;
import com.projetofef.domains.dtos.RecebimentoDTO;
import com.projetofef.mappers.RecebimentoMapper;
import com.projetofef.repositories.ContaBancariaRepository;
import com.projetofef.repositories.LancamentoRepository;
import com.projetofef.repositories.RecebimentoRepository;
import com.projetofef.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecebimentoService {

    private final RecebimentoRepository repository;
    private final LancamentoRepository lancamentoRepository;
    private final ContaBancariaRepository contaRepository;

    public RecebimentoService(RecebimentoRepository repository,
                              LancamentoRepository lancamentoRepository,
                              ContaBancariaRepository contaRepository) {
        this.repository = repository;
        this.lancamentoRepository = lancamentoRepository;
        this.contaRepository = contaRepository;
    }

    public List<RecebimentoDTO> findAll() {
        return RecebimentoMapper.toDtoList(repository.findAll());
    }

    public RecebimentoDTO findById(Long id) {
        Recebimento rec = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Recebimento não encontrado: " + id));
        return RecebimentoMapper.toDto(rec);
    }

    @Transactional
    public RecebimentoDTO create(RecebimentoDTO dto) {
        Recebimento rec = toEntityWithRelations(dto);
        rec = repository.save(rec);
        return RecebimentoMapper.toDto(rec);
    }

    @Transactional
    public RecebimentoDTO update(Long id, RecebimentoDTO dto) {
        Recebimento existing = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Recebimento não encontrado: " + id));

        existing.setLancamento(lancamentoRepository.findById(dto.getLancamentoId())
                .orElseThrow(() -> new DataIntegrityException("Lançamento não encontrado: " + dto.getLancamentoId())));
        existing.setContaDestino(contaRepository.findById(dto.getContaDestinoId())
                .orElseThrow(() -> new DataIntegrityException("Conta bancária não encontrada: " + dto.getContaDestinoId())));
        existing.setDataRecebimento(dto.getDataRecebimento());
        existing.setValor(dto.getValor());
        existing.setObservacao(dto.getObservacao());

        existing = repository.save(existing);
        return RecebimentoMapper.toDto(existing);
    }

    @Transactional
    public void delete(Long id) {
        Recebimento rec = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Recebimento não encontrado: " + id));
        repository.delete(rec);
    }

    private Recebimento toEntityWithRelations(RecebimentoDTO dto) {
        Lancamento lancamento = lancamentoRepository.findById(dto.getLancamentoId())
                .orElseThrow(() -> new DataIntegrityException("Lançamento não encontrado: " + dto.getLancamentoId()));

        ContaBancaria conta = contaRepository.findById(dto.getContaDestinoId())
                .orElseThrow(() -> new DataIntegrityException("Conta bancária não encontrada: " + dto.getContaDestinoId()));

        Recebimento rec = new Recebimento();
        rec.setLancamento(lancamento);
        rec.setContaDestino(conta);
        rec.setDataRecebimento(dto.getDataRecebimento());
        rec.setValor(dto.getValor());
        rec.setObservacao(dto.getObservacao());

        return rec;
    }
}
