package com.mappers;

import com.projetofef.domains.CentroCusto;
import com.projetofef.domains.Usuario;
import com.projetofef.domains.dtos.CentroCustoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CentroCustoMapper {

    private CentroCustoMapper() {}

    public static CentroCustoDTO toDto(CentroCusto e) {
        if (e == null) return null;
        CentroCustoDTO dto = new CentroCustoDTO();
        dto.setId(e.getId());
        dto.setUsuarioId(e.getUsuario() != null ? e.getUsuario().getId() : null);
        dto.setNome(e.getNome());
        dto.setCodigo(e.getCodigo());
        dto.setAtivo(e.getAtivo());
        return dto;
    }

    public static CentroCusto toEntity(CentroCustoDTO dto, Usuario usuario) {
        if (dto == null) return null;
        CentroCusto e = new CentroCusto();
        e.setId(dto.getId());
        e.setUsuario(usuario);
        e.setNome(dto.getNome() != null ? dto.getNome().trim() : null);
        e.setCodigo(dto.getCodigo() != null ? dto.getCodigo().trim() : null);
        e.setAtivo(dto.getAtivo());
        return e;
    }

    public static void copyToEntity(CentroCustoDTO dto, CentroCusto target, Usuario usuario) {
        if (dto == null || target == null) return;
        target.setUsuario(usuario);
        target.setNome(dto.getNome() != null ? dto.getNome().trim() : null);
        target.setCodigo(dto.getCodigo() != null ? dto.getCodigo().trim() : null);
        target.setAtivo(dto.getAtivo());
    }

    public static List<CentroCustoDTO> toDtoList(Collection<CentroCusto> entities) {
        if (entities == null) return List.of();
        return entities.stream().filter(Objects::nonNull).map(CentroCustoMapper::toDto).collect(Collectors.toList());
    }

    public static List<CentroCusto> toEntityList(Collection<CentroCustoDTO> dtos, List<Usuario> usuarios) {
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

    public static Page<CentroCustoDTO> toDtoPage(Page<CentroCusto> page) {
        List<CentroCustoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
