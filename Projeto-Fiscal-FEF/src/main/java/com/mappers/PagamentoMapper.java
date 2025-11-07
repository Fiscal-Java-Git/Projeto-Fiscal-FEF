package com.projetofef.mappers;

import com.projetofef.domains.ContaBancaria;
import com.projetofef.domains.Lancamento;
import com.projetofef.domains.Pagamento;
import com.projetofef.domains.dtos.PagamentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class PagamentoMapper {

    private PagamentoMapper() {}

    public static PagamentoDTO toDto(Pagamento e) {
        if (e == null) return null;

        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(e.getId());
        dto.setLancamentoId(e.getLancamento() != null ? e.getLancamento().getId() : null);
        dto.setContaOrigemId(e.getContaOrigem() != null ? e.getContaOrigem().getId() : null);
        dto.setDataPagamento(e.getDataPagamento() != null ? e.getDataPagamento().toLocalDate() : null);
        dto.setValorPago(e.getValorPago() != null ? e.getValorPago().doubleValue() : 0.0);
        dto.setObservacao(e.getObservacao());
        return dto;
    }

    public static Pagamento toEntity(PagamentoDTO dto, Lancamento lancamento, ContaBancaria contaOrigem) {
        if (dto == null) return null;

        Pagamento e = new Pagamento();
        e.setId(dto.getId());
        e.setLancamento(lancamento);
        e.setContaOrigem(contaOrigem);

        // Converte LocalDate do DTO para LocalDateTime (meia-noite)
        e.setDataPagamento(dto.getDataPagamento() != null ? dto.getDataPagamento().atStartOfDay() : null);

        e.setValorPago(dto.getValorPago() != null ? BigDecimal.valueOf(dto.getValorPago()) : BigDecimal.ZERO);
        e.setObservacao(dto.getObservacao());
        return e;
    }

    public static void copyToEntity(PagamentoDTO dto, Pagamento target, Lancamento lancamento, ContaBancaria contaOrigem) {
        if (dto == null || target == null) return;

        target.setLancamento(lancamento);
        target.setContaOrigem(contaOrigem);
        target.setDataPagamento(dto.getDataPagamento() != null ? dto.getDataPagamento().atStartOfDay() : null);
        target.setValorPago(dto.getValorPago() != null ? BigDecimal.valueOf(dto.getValorPago()) : BigDecimal.ZERO);
        target.setObservacao(dto.getObservacao());
    }

    public static List<PagamentoDTO> toDtoList(Collection<Pagamento> entities) {
        if (entities == null) return List.of();
        return entities.stream().filter(Objects::nonNull).map(PagamentoMapper::toDto).collect(Collectors.toList());
    }

    public static Page<PagamentoDTO> toDtoPage(Page<Pagamento> page) {
        List<PagamentoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
