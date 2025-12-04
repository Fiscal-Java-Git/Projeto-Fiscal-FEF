package com.mappers;

import com.domains.Usuario;
import com.domains.dtos.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class UsuarioMapper {

    private UsuarioMapper() {}

    public static UsuarioDTO toDto(Usuario e) {
        if (e == null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        dto.setEmail(e.getEmail());
        return dto;
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        Usuario e = new Usuario();
        e.setId(dto.getId());
        e.setNome(dto.getNome() == null ? null : dto.getNome().trim());
        e.setEmail(dto.getEmail() == null ? null : dto.getEmail().trim());
        return e;
    }

    public static void copyToEntity(UsuarioDTO dto, Usuario target) {
        if (dto == null || target == null) return;
        target.setNome(dto.getNome() == null ? null : dto.getNome().trim());
        target.setEmail(dto.getEmail() == null ? null : dto.getEmail().trim());
    }

    public static List<UsuarioDTO> toDtoList(Collection<Usuario> entities) {
        if (entities == null) return List.of();
        return entities.stream().filter(Objects::nonNull).map(UsuarioMapper::toDto).collect(Collectors.toList());
    }

    public static List<Usuario> toEntityList(Collection<UsuarioDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream().filter(Objects::nonNull).map(UsuarioMapper::toEntity).collect(Collectors.toList());
    }

    public static Page<UsuarioDTO> toDtoPage(Page<Usuario> page) {
        List<UsuarioDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
