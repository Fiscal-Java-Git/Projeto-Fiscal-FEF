package com.mappers;

import com.projetofef.domains.Lancamento;
import com.projetofef.domains.dtos.LancamentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class LancamentoMapper {

    private LancamentoMapper() {}

    public static LancamentoDTO toDto(Lancamento e) {
        if (e == null) return null;

        LancamentoDTO dto = new LancamentoDTO();
        dto.setId(e.getId());
        dto.setTipo(e.getTipo());
        dto.setDescricao(e.getDescricao());
        dto.setEntidadeId(e.getEntidade() != null ? safeGetId(e.getEntidade()) : null);
        dto.setCentroCustoId(e.getCentroCusto() != null ? safeGetId(e.getCentroCusto()) : null);
        dto.setValor(e.getValor());
        dto.setDataCompetencia(e.getDataCompetencia());
        dto.setDataVencimento(e.getDataVencimento());
        dto.setMeioPagamento(e.getMeioPagamento());
        dto.setContaBancariaId(e.getContaBancaria() != null ? safeGetId(e.getContaBancaria()) : null);
        dto.setCartaoCreditoId(e.getCartaoCredito() != null ? safeGetId(e.getCartaoCredito()) : null);
        dto.setStatus(e.getStatus());
        dto.setValorBaixado(e.getValorBaixado());

        return dto;
    }

    public static Lancamento toEntity(LancamentoDTO dto) {
        if (dto == null) return null;

        Lancamento e = new Lancamento();
        e.setId(dto.getId());
        e.setTipo(dto.getTipo());
        e.setDescricao(dto.getDescricao() != null ? dto.getDescricao().trim() : null);
        e.setValor(dto.getValor());
        e.setDataCompetencia(dto.getDataCompetencia());
        e.setDataVencimento(dto.getDataVencimento());
        e.setMeioPagamento(dto.getMeioPagamento());
        e.setStatus(dto.getStatus());
        e.setValorBaixado(dto.getValorBaixado());

        return e;
    }

    public static void copyToEntity(LancamentoDTO dto, Lancamento target) {
        if (dto == null || target == null) return;

        target.setTipo(dto.getTipo());
        target.setDescricao(dto.getDescricao() != null ? dto.getDescricao().trim() : null);
        target.setValor(dto.getValor());
        target.setDataCompetencia(dto.getDataCompetencia());
        target.setDataVencimento(dto.getDataVencimento());
        target.setMeioPagamento(dto.getMeioPagamento());
        target.setStatus(dto.getStatus());
        target.setValorBaixado(dto.getValorBaixado());
    }

    public static List<LancamentoDTO> toDtoList(Collection<Lancamento> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(LancamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Lancamento> toEntityList(Collection<LancamentoDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(LancamentoMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static Page<LancamentoDTO> toDtoPage(Page<Lancamento> page) {
        List<LancamentoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

    private static Long safeGetId(Object entity) {
        try {
            return (Long) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception ex) {
            return null;
        }
    }
}
