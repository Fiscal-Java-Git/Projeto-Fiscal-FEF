package com.projetofef.mappers;

import com.projetofef.domains.CartaoCredito;
import com.projetofef.domains.FaturaCartao;
import com.projetofef.domains.dtos.FaturaCartaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class FaturaCartaoMapper {

    private FaturaCartaoMapper() {}

    public static FaturaCartaoDTO toDto(FaturaCartao e) {
        if (e == null) return null;

        FaturaCartaoDTO dto = new FaturaCartaoDTO();
        dto.setId(e.getId());
        dto.setCartaoId(e.getCartao() != null ? e.getCartao().getId() : null);

        dto.setCompetencia(e.getCompetencia() != null ? e.getCompetencia().toLocalDate() : null);
        dto.setDataFechamento(e.getDataFechamento() != null ? e.getDataFechamento().toLocalDate() : null);
        dto.setDataVencimento(e.getDataVencimento() != null ? e.getDataVencimento().toLocalDate() : null);

        dto.setValorTotal(e.getValorTotal() != null ? e.getValorTotal().doubleValue() : 0.0);
        dto.setStatus(e.getStatus());

        return dto;
    }

    public static FaturaCartao toEntity(FaturaCartaoDTO dto, CartaoCredito cartao) {
        if (dto == null) return null;

        FaturaCartao e = new FaturaCartao();
        e.setId(dto.getId());
        e.setCartao(cartao);

        e.setCompetencia(dto.getCompetencia() != null ? dto.getCompetencia().atStartOfDay() : null);
        e.setDataFechamento(dto.getDataFechamento() != null ? dto.getDataFechamento().atStartOfDay() : null);
        e.setDataVencimento(dto.getDataVencimento() != null ? dto.getDataVencimento().atStartOfDay() : null);

        e.setValorTotal(dto.getValorTotal() != null ? BigDecimal.valueOf(dto.getValorTotal()) : BigDecimal.ZERO);
        e.setStatus(dto.getStatus());

        return e;
    }

    public static void copyToEntity(FaturaCartaoDTO dto, FaturaCartao target, CartaoCredito cartao) {
        if (dto == null || target == null) return;

        target.setCartao(cartao);
        target.setCompetencia(dto.getCompetencia() != null ? dto.getCompetencia().atStartOfDay() : null);
        target.setDataFechamento(dto.getDataFechamento() != null ? dto.getDataFechamento().atStartOfDay() : null);
        target.setDataVencimento(dto.getDataVencimento() != null ? dto.getDataVencimento().atStartOfDay() : null);
        target.setValorTotal(dto.getValorTotal() != null ? BigDecimal.valueOf(dto.getValorTotal()) : BigDecimal.ZERO);
        target.setStatus(dto.getStatus());
    }

    public static List<FaturaCartaoDTO> toDtoList(Collection<FaturaCartao> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(FaturaCartaoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<FaturaCartao> toEntityList(Collection<FaturaCartaoDTO> dtos, List<CartaoCredito> cartoes) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(dto -> {
                    CartaoCredito cartao = cartoes.stream()
                            .filter(c -> Objects.equals(c.getId(), dto.getCartaoId()))
                            .findFirst()
                            .orElse(null);
                    return toEntity(dto, cartao);
                })
                .collect(Collectors.toList());
    }

    public static Page<FaturaCartaoDTO> toDtoPage(Page<FaturaCartao> page) {
        List<FaturaCartaoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
