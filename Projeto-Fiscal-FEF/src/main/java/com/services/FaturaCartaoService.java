package com.services;

import com.projetofef.domains.CartaoCredito;
import com.projetofef.domains.FaturaCartao;
import com.projetofef.domains.dtos.FaturaCartaoDTO;
import com.projetofef.mappers.FaturaCartaoMapper;
import com.projetofef.repositories.CartaoCreditoRepository;
import com.projetofef.repositories.FaturaCartaoRepository;
import com.projetofef.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FaturaCartaoService {

    private final FaturaCartaoRepository repository;
    private final CartaoCreditoRepository cartaoRepository;

    public FaturaCartaoService(FaturaCartaoRepository repository, CartaoCreditoRepository cartaoRepository) {
        this.repository = repository;
        this.cartaoRepository = cartaoRepository;
    }

    public List<FaturaCartaoDTO> findAll() {
        return FaturaCartaoMapper.toDtoList(repository.findAll());
    }

    public FaturaCartaoDTO findById(Long id) {
        FaturaCartao fatura = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Fatura não encontrada: " + id));
        return FaturaCartaoMapper.toDto(fatura);
    }

    @Transactional
    public FaturaCartaoDTO create(FaturaCartaoDTO dto) {
        CartaoCredito cartao = cartaoRepository.findById(dto.getCartaoId())
                .orElseThrow(() -> new DataIntegrityException("Cartão não encontrado: " + dto.getCartaoId()));

        FaturaCartao fatura = FaturaCartaoMapper.toEntity(dto, cartao);
        fatura = repository.save(fatura);
        return FaturaCartaoMapper.toDto(fatura);
    }

    @Transactional
    public FaturaCartaoDTO update(Long id, FaturaCartaoDTO dto) {
        FaturaCartao fatura = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Fatura não encontrada: " + id));

        CartaoCredito cartao = cartaoRepository.findById(dto.getCartaoId())
                .orElseThrow(() -> new DataIntegrityException("Cartão não encontrado: " + dto.getCartaoId()));

        FaturaCartaoMapper.copyToEntity(dto, fatura, cartao);
        fatura = repository.save(fatura);
        return FaturaCartaoMapper.toDto(fatura);
    }

    @Transactional
    public void delete(Long id) {
        FaturaCartao fatura = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Fatura não encontrada: " + id));
        repository.delete(fatura);
    }
}
