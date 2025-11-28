package com.mappers;

import com.projetofef.domains.ContaBancaria;
import com.projetofef.domains.Transferencia;
import com.projetofef.domains.dtos.TransferenciaDTO;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class TransferenciaMapper {

    private TransferenciaMapper() {}

    public static TransferenciaDTO toDto(Transferencia e) {
        if (e == null) return null;

        TransferenciaDTO dto = new TransferenciaDTO();
        dto.setId(e.getId());
        dto.setContaOrigemId(e.getContaOrigem() != null ? e.getContaOrigem().getId() : null);
        dto.setContaDestinoId(e.getContaDestino() != null ? e.getContaDestino().getId() : null);
        dto.setData(e.getData());
        dto.setValor(e.getValor());
        dto.setObservacao(e.getObservacao());
        return dto;
    }

    public static Transferencia toEntity(TransferenciaDTO dto, ContaBancaria origem, ContaBancaria destino) {
        if (dto == null) return null;

        Transferencia e = new Transferencia();
        e.setId(dto.getId());
        e.setContaOrigem(origem);
        e.setContaDestino(destino);
        e.setData(dto.getData());
        e.setValor(dto.getValor());
        e.setObservacao(dto.getObservacao());
        return e;
    }

    public static void copyToEntity(TransferenciaDTO dto, Transferencia target, ContaBancaria origem, ContaBancaria destino) {
        if (dto == null || target == null) return;

        target.setContaOrigem(origem);
        target.setContaDestino(destino);
        target.setData(dto.getData());
        target.setValor(dto.getValor());
        target.setObservacao(dto.getObservacao());
    }

    public static List<TransferenciaDTO> toDtoList(Collection<Transferencia> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(TransferenciaMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Transferencia> toEntityList(Collection<TransferenciaDTO> dtos,
                                                   List<ContaBancaria> origens,
                                                   List<ContaBancaria> destinos) {
        if (dtos == null) return List.of();

        return dtos.stream()
                .filter(Objects::nonNull)
                .map(dto -> {
                    ContaBancaria origem = origens.stream()
                            .filter(c -> Objects.equals(c.getId(), dto.getContaOrigemId()))
                            .findFirst()
                            .orElse(null);

                    ContaBancaria destino = destinos.stream()
                            .filter(c -> Objects.equals(c.getId(), dto.getContaDestinoId()))
                            .findFirst()
                            .orElse(null);

                    return toEntity(dto, origem, destino);
                })
                .collect(Collectors.toList());
    }
}
