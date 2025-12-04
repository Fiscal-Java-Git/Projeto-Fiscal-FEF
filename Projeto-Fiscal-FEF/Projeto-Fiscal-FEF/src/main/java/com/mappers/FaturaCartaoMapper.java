package com.mappers;

import com.domains.CartaoCredito;
import com.domains.FaturaCartao;
import com.domains.dtos.FaturaCartaoDTO;

import java.util.List;

public class FaturaCartaoMapper {

    public static FaturaCartaoDTO toDto(FaturaCartao f) {
        if (f == null) return null;

        FaturaCartaoDTO dto = new FaturaCartaoDTO();
        dto.setId(f.getId());
        dto.setCartaoId(f.getCartao().getId());
        dto.setCompetencia(f.getCompetencia());
        dto.setDataFechamento(f.getDataFechamento());
        dto.setDataVencimento(f.getDataVencimento());
        dto.setValorTotal(f.getValorTotal());
        dto.setStatus(f.getStatus());
        dto.setDataPagamento(f.getDataPagamento());

        return dto;
    }

    public static List<FaturaCartaoDTO> toDtoList(List<FaturaCartao> list) {
        return list.stream().map(FaturaCartaoMapper::toDto).toList();
    }

    public static FaturaCartao toEntity(FaturaCartaoDTO dto, CartaoCredito cartao) {
        FaturaCartao f = new FaturaCartao();

        f.setCartao(cartao);
        f.setCompetencia(dto.getCompetencia());
        f.setDataFechamento(dto.getDataFechamento());
        f.setDataVencimento(dto.getDataVencimento());
        f.setValorTotal(dto.getValorTotal());
        f.setStatus(dto.getStatus());
        f.setDataPagamento(dto.getDataPagamento());

        return f;
    }

    public static void copyToEntity(FaturaCartaoDTO dto, FaturaCartao f, CartaoCredito cartao) {
        f.setCartao(cartao);
        f.setCompetencia(dto.getCompetencia());
        f.setDataFechamento(dto.getDataFechamento());
        f.setDataVencimento(dto.getDataVencimento());
        f.setValorTotal(dto.getValorTotal());
        f.setStatus(dto.getStatus());
        f.setDataPagamento(dto.getDataPagamento());
    }
}
