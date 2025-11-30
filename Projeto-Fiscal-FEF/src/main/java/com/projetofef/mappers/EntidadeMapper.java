package com.projetofef.mappers;

import com.projetofef.domains.Entidade;
import com.projetofef.domains.Usuario;
import com.projetofef.domains.dtos.EntidadeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class EntidadeMapper {

    private EntidadeMapper() {}

    public static EntidadeDTO toDto(Entidade e) {
        if (e == null) return null;
        EntidadeDTO dto = new EntidadeDTO();
        dto.setId(e.getId());
        dto.setUsuarioId(e.getUsuario() != null ? e.getUsuario().getId() : null);
        dto.setNome(e.getNome());
        dto.setDocumento(e.getDocumento());
        dto.setTipo(e.getTipo());
        dto.setAtivo(e.getAtivo());
        return dto;
    }

    public static Entidade toEntity(EntidadeDTO dto, Usuario usuario) {
        if (dto == null) return null;
        Entidade e = new Entidade();
        e.setId(dto.getId());
        e.setUsuario(usuario);
        e.setNome(dto.getNome() != null ? dto.getNome().trim() : null);
        e.setDocumento(dto.getDocumento() != null ? dto.getDocumento().trim() : null);
        e.setTipo(dto.getTipo());
        e.setAtivo(dto.getAtivo());
        return e;
    }

    public static void copyToEntity(EntidadeDTO dto, Entidade target, Usuario usuario) {
        if (dto == null || target == null) return;
        target.setUsuario(usuario);
        target.setNome(dto.getNome() != null ? dto.getNome().trim() : null);
        target.setDocumento(dto.getDocumento() != null ? dto.getDocumento().trim() : null);
        target.setTipo(dto.getTipo());
        target.setAtivo(dto.getAtivo());
    }

    public static List<EntidadeDTO> toDtoList(Collection<Entidade> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(EntidadeMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Entidade> toEntityList(Collection<EntidadeDTO> dtos, List<Usuario> usuarios) {
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

    public static Page<EntidadeDTO> toDtoPage(Page<Entidade> page) {
        List<EntidadeDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
