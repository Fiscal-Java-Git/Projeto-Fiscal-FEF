package com.services;

import com.domains.ContaBancaria;
import com.domains.Lancamento;
import com.domains.Pagamento;
import com.domains.dtos.PagamentoDTO;
import com.mappers.PagamentoMapper;
import com.repositories.ContaBancariaRepository;
import com.repositories.LancamentoRepository;
import com.repositories.PagamentoRepository;
import com.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;
    private final LancamentoRepository lancamentoRepository;
    private final ContaBancariaRepository contaRepository;

    public PagamentoService(PagamentoRepository repository,
                            LancamentoRepository lancamentoRepository,
                            ContaBancariaRepository contaRepository) {
        this.repository = repository;
        this.lancamentoRepository = lancamentoRepository;
        this.contaRepository = contaRepository;
    }

    public List<PagamentoDTO> findAll() {
        return PagamentoMapper.toDtoList(repository.findAll());
    }

    public PagamentoDTO findById(Long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Pagamento não encontrado: " + id));
        return PagamentoMapper.toDto(pagamento);
    }

    @Transactional
    public PagamentoDTO create(PagamentoDTO dto) {
        Lancamento lancamento = lancamentoRepository.findById(dto.getLancamentoId())
                .orElseThrow(() -> new DataIntegrityException("Lançamento não encontrado: " + dto.getLancamentoId()));
        ContaBancaria conta = contaRepository.findById(dto.getContaOrigemId())
                .orElseThrow(() -> new DataIntegrityException("Conta bancária não encontrada: " + dto.getContaOrigemId()));

        Pagamento pagamento = PagamentoMapper.toEntity(dto, lancamento, conta);
        pagamento = repository.save(pagamento);
        return PagamentoMapper.toDto(pagamento);
    }

    @Transactional
    public PagamentoDTO update(Long id, PagamentoDTO dto) {
        Pagamento existing = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Pagamento não encontrado: " + id));

        Lancamento lancamento = lancamentoRepository.findById(dto.getLancamentoId())
                .orElseThrow(() -> new DataIntegrityException("Lançamento não encontrado: " + dto.getLancamentoId()));
        ContaBancaria conta = contaRepository.findById(dto.getContaOrigemId())
                .orElseThrow(() -> new DataIntegrityException("Conta bancária não encontrada: " + dto.getContaOrigemId()));

        PagamentoMapper.copyToEntity(dto, existing, lancamento, conta);
        existing = repository.save(existing);
        return PagamentoMapper.toDto(existing);
    }

    @Transactional
    public void delete(Long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Pagamento não encontrado: " + id));
        repository.delete(pagamento);
    }
}
