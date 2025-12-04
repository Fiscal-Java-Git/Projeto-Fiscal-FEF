package com.services;

import com.domains.CartaoCredito;
import com.domains.FaturaCartao;
import com.domains.enums.StatusFatura;
import com.domains.dtos.FaturaCartaoDTO;
import com.mappers.FaturaCartaoMapper;
import com.repositories.CartaoCreditoRepository;
import com.repositories.FaturaCartaoRepository;
import com.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FaturaCartaoService {

    private final FaturaCartaoRepository repository;
    private final CartaoCreditoRepository cartaoRepository;

    public FaturaCartaoService(FaturaCartaoRepository repository, CartaoCreditoRepository cartaoRepository) {
        this.repository = repository;
        this.cartaoRepository = cartaoRepository;
    }

    public List<FaturaCartaoDTO> findAllByCartaoId(Long cartaoId) {
        List<FaturaCartao> list = repository.findByCartao_Id(cartaoId);
        return FaturaCartaoMapper.toDtoList(list);
    }

    public List<FaturaCartaoDTO> findAll() {
        return FaturaCartaoMapper.toDtoList(repository.findAll());
    }

    public FaturaCartaoDTO findById(Long id) {
        FaturaCartao f = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Fatura não encontrada: " + id));

        return FaturaCartaoMapper.toDto(f);
    }

    @Transactional
    public FaturaCartaoDTO create(FaturaCartaoDTO dto) {
        CartaoCredito cartao = cartaoRepository.findById(dto.getCartaoId())
                .orElseThrow(() -> new DataIntegrityException("Cartão não encontrado: " + dto.getCartaoId()));

        FaturaCartao f = FaturaCartaoMapper.toEntity(dto, cartao);
        f = repository.save(f);

        return FaturaCartaoMapper.toDto(f);
    }


    @Transactional
    public FaturaCartaoDTO update(Long id, FaturaCartaoDTO dto) {
        FaturaCartao f = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Fatura não encontrada: " + id));

        CartaoCredito cartao = cartaoRepository.findById(dto.getCartaoId())
                .orElseThrow(() -> new DataIntegrityException("Cartão não encontrado: " + dto.getCartaoId()));

        FaturaCartaoMapper.copyToEntity(dto, f, cartao);
        f = repository.save(f);

        return FaturaCartaoMapper.toDto(f);
    }

    @Transactional
    public void delete(Long id) {
        FaturaCartao f = repository.findById(id)
                .orElseThrow(() -> new DataIntegrityException("Fatura não encontrada: " + id));

        repository.delete(f);
    }

    @Transactional
    public FaturaCartaoDTO fecharFatura(Long cartaoId) {

        CartaoCredito cartao = cartaoRepository.findById(cartaoId)
                .orElseThrow(() -> new DataIntegrityException("Cartão não encontrado: " + cartaoId));

        LocalDate competencia = LocalDate.now().withDayOfMonth(1);

        var existente = repository.findByCartao_IdAndCompetencia(cartaoId, competencia);

        if (existente.isPresent()) {
            FaturaCartao f = existente.get();

            if (f.getStatus() == StatusFatura.FECHADA) {
                throw new DataIntegrityException("A fatura deste mês já está fechada.");
            }

            f.setStatus(StatusFatura.FECHADA);
            f.setDataFechamento(LocalDateTime.now());
            repository.save(f);

            return FaturaCartaoMapper.toDto(f);
        }

        FaturaCartao f = new FaturaCartao();
        f.setCartao(cartao);
        f.setCompetencia(competencia);
        f.setDataFechamento(LocalDateTime.now());
        f.setDataVencimento(competencia.plusMonths(1).withDayOfMonth(5).atStartOfDay());
        f.setStatus(StatusFatura.FECHADA);
        f.setValorTotal(BigDecimal.ZERO);

        repository.save(f);

        return FaturaCartaoMapper.toDto(f);
    }

    @Transactional
    public FaturaCartaoDTO pagarFatura(Long cartaoId, Long faturaId) {

        FaturaCartao f = repository.findById(faturaId)
                .orElseThrow(() -> new DataIntegrityException("Fatura não encontrada: " + faturaId));

        if (!f.getCartao().getId().equals(cartaoId)) {
            throw new DataIntegrityException("A fatura não pertence ao cartão informado.");
        }

        if (f.getStatus() == StatusFatura.PAGA) {
            throw new DataIntegrityException("A fatura já está paga.");
        }

        f.setStatus(StatusFatura.PAGA);
        f.setDataPagamento(LocalDate.now());

        repository.save(f);

        return FaturaCartaoMapper.toDto(f);
    }
}
