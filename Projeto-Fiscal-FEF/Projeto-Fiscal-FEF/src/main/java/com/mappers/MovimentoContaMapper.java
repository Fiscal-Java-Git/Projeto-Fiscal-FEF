package com.mappers;

import com.domains.MovimentoConta;
import com.domains.dtos.MovimentoContaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class MovimentoContaMapper {

    private MovimentoContaMapper() {}

    public static MovimentoContaDTO toDto(MovimentoConta e) {
        if (e == null) return null;

        MovimentoContaDTO dto = new MovimentoContaDTO();
        dto.setId(e.getId());
        dto.setContaId(e.getConta() != null ? e.getConta().getId() : null);
        dto.setTipo(e.getTipo());
        dto.setValor(e.getValor());
        dto.setHistorico(e.getHistorico());
        dto.setReferenciaId(e.getReferenciaId());
        dto.setReferenciaTipo(e.getReferenciaTipo());
        dto.setDataMovimento(e.getDataMovimento());
        dto.setCriadoEm(e.getCriadoEm());
        dto.setAtualizadoEm(e.getAtualizadoEm());

        return dto;
    }

    public static MovimentoConta toEntity(MovimentoContaDTO dto) {
        if (dto == null) return null;

        MovimentoConta e = new MovimentoConta();
        e.setId(dto.getId());
        e.setTipo(dto.getTipo());
        e.setValor(dto.getValor());
        e.setHistorico(dto.getHistorico() != null ? dto.getHistorico().trim() : null);
        e.setReferenciaId(dto.getReferenciaId());
        e.setReferenciaTipo(dto.getReferenciaTipo());
        e.setDataMovimento(dto.getDataMovimento());
        e.setCriadoEm(dto.getCriadoEm());
        e.setAtualizadoEm(dto.getAtualizadoEm());

        return e;
    }

    public static void copyToEntity(MovimentoContaDTO dto, MovimentoConta target) {
        if (dto == null || target == null) return;

        target.setTipo(dto.getTipo());
        target.setValor(dto.getValor());
        target.setHistorico(dto.getHistorico() != null ? dto.getHistorico().trim() : null);
        target.setReferenciaId(dto.getReferenciaId());
        target.setReferenciaTipo(dto.getReferenciaTipo());
        target.setDataMovimento(dto.getDataMovimento());
    }

    public static List<MovimentoContaDTO> toDtoList(Collection<MovimentoConta> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(MovimentoContaMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<MovimentoConta> toEntityList(Collection<MovimentoContaDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(MovimentoContaMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static Page<MovimentoContaDTO> toDtoPage(Page<MovimentoConta> page) {
        List<MovimentoContaDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
