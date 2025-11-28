package com.mappers;

import com.projetofef.domains.ContaBancaria;
import com.projetofef.domains.Usuario;
import com.projetofef.domains.dtos.ContaBancariaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ContaBancariaMapper {

    private ContaBancariaMapper() {}

    public static ContaBancariaDTO toDto(ContaBancaria e) {
        if (e == null) return null;
        ContaBancariaDTO dto = new ContaBancariaDTO();
        dto.setId(e.getId());
        dto.setUsuarioId(e.getUsuario() != null ? e.getUsuario().getId() : null);
        dto.setInstituicao(e.getInstituicao());
        dto.setAgencia(e.getAgencia());
        dto.setNumero(e.getNumero());
        dto.setApelido(e.getApelido());
        dto.setSaldoInicial(e.getSaldoInicial() != null ? e.getSaldoInicial().doubleValue() : 0.0);
        dto.setDataSaldoInicial(e.getDataSaldoInicial());
        dto.setAtiva(e.getAtiva());
        return dto;
    }

    public static ContaBancaria toEntity(ContaBancariaDTO dto, Usuario usuario) {
        if (dto == null) return null;
        ContaBancaria e = new ContaBancaria();
        e.setId(dto.getId());
        e.setUsuario(usuario);
        e.setInstituicao(dto.getInstituicao() != null ? dto.getInstituicao().trim() : null);
        e.setAgencia(dto.getAgencia() != null ? dto.getAgencia().trim() : null);
        e.setNumero(dto.getNumero() != null ? dto.getNumero().trim() : null);
        e.setApelido(dto.getApelido() != null ? dto.getApelido().trim() : null);
        e.setSaldoInicial(dto.getSaldoInicial() != null ? BigDecimal.valueOf(dto.getSaldoInicial()) : BigDecimal.ZERO);
        e.setDataSaldoInicial(dto.getDataSaldoInicial());
        e.setAtiva(dto.getAtiva());
        return e;
    }

    public static void copyToEntity(ContaBancariaDTO dto, ContaBancaria target, Usuario usuario) {
        if (dto == null || target == null) return;
        target.setUsuario(usuario);
        target.setInstituicao(dto.getInstituicao() != null ? dto.getInstituicao().trim() : null);
        target.setAgencia(dto.getAgencia() != null ? dto.getAgencia().trim() : null);
        target.setNumero(dto.getNumero() != null ? dto.getNumero().trim() : null);
        target.setApelido(dto.getApelido() != null ? dto.getApelido().trim() : null);
        target.setSaldoInicial(dto.getSaldoInicial() != null ? BigDecimal.valueOf(dto.getSaldoInicial()) : BigDecimal.ZERO);
        target.setDataSaldoInicial(dto.getDataSaldoInicial());
        target.setAtiva(dto.getAtiva());
    }

    public static List<ContaBancariaDTO> toDtoList(Collection<ContaBancaria> entities) {
        if (entities == null) return List.of();
        return entities.stream().filter(Objects::nonNull).map(ContaBancariaMapper::toDto).collect(Collectors.toList());
    }

    public static List<ContaBancaria> toEntityList(Collection<ContaBancariaDTO> dtos, List<Usuario> usuarios) {
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

    public static Page<ContaBancariaDTO> toDtoPage(Page<ContaBancaria> page) {
        List<ContaBancariaDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
