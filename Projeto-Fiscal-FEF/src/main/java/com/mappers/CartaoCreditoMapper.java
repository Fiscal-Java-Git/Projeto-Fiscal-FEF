package com.mappers;

import com.projetofef.domains.CartaoCredito;
import com.projetofef.domains.Usuario;
import com.projetofef.domains.dtos.CartaoCreditoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CartaoCreditoMapper {

    private CartaoCreditoMapper() {}

    public static CartaoCreditoDTO toDto(CartaoCredito e) {
        if (e == null) return null;
        CartaoCreditoDTO dto = new CartaoCreditoDTO();
        dto.setId(e.getId());
        dto.setUsuarioId(e.getUsuario() != null ? e.getUsuario().getId() : null);
        dto.setBandeira(e.getBandeira());
        dto.setEmissor(e.getEmissor());
        dto.setApelido(e.getApelido());
        dto.setFechamentoFaturaDia(e.getFechamentoFaturaDia());
        dto.setVencimentoFaturaDia(e.getVencimentoFaturaDia());
        dto.setAtivo(e.getAtivo());
        return dto;
    }

    public static CartaoCredito toEntity(CartaoCreditoDTO dto, Usuario usuario) {
        if (dto == null) return null;
        CartaoCredito e = new CartaoCredito();
        e.setId(dto.getId());
        e.setUsuario(usuario);
        e.setBandeira(dto.getBandeira() != null ? dto.getBandeira().trim() : null);
        e.setEmissor(dto.getEmissor() != null ? dto.getEmissor().trim() : null);
        e.setApelido(dto.getApelido() != null ? dto.getApelido().trim() : null);
        e.setFechamentoFaturaDia(dto.getFechamentoFaturaDia());
        e.setVencimentoFaturaDia(dto.getVencimentoFaturaDia());
        e.setAtivo(dto.getAtivo());
        return e;
    }

    public static void copyToEntity(CartaoCreditoDTO dto, CartaoCredito target, Usuario usuario) {
        if (dto == null || target == null) return;
        target.setUsuario(usuario);
        target.setBandeira(dto.getBandeira() != null ? dto.getBandeira().trim() : null);
        target.setEmissor(dto.getEmissor() != null ? dto.getEmissor().trim() : null);
        target.setApelido(dto.getApelido() != null ? dto.getApelido().trim() : null);
        target.setFechamentoFaturaDia(dto.getFechamentoFaturaDia());
        target.setVencimentoFaturaDia(dto.getVencimentoFaturaDia());
        target.setAtivo(dto.getAtivo());
    }

    public static List<CartaoCreditoDTO> toDtoList(Collection<CartaoCredito> entities) {
        if (entities == null) return List.of();
        return entities.stream().filter(Objects::nonNull).map(CartaoCreditoMapper::toDto).collect(Collectors.toList());
    }

    public static List<CartaoCredito> toEntityList(Collection<CartaoCreditoDTO> dtos, List<Usuario> usuarios) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(dto -> {
                    Usuario usuario = usuarios.stream()
                            .filter(u -> Objects.equals(u.getId(), dto.getUsuarioId()))
                            .findFirst()
                            .orElse(null);
                    return toEntity(dto, usuario);
                })
                .collect(Collectors.toList());
    }

    public static Page<CartaoCreditoDTO> toDtoPage(Page<CartaoCredito> page) {
        List<CartaoCreditoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
