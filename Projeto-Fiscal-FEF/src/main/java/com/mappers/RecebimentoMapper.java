package com.projetofef.mappers;

import com.projetofef.domains.Recebimento;
import com.projetofef.domains.ContaBancaria;
import com.projetofef.domains.Lancamento;
import com.projetofef.domains.dtos.RecebimentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class RecebimentoMapper {

    private RecebimentoMapper() {}

    public static RecebimentoDTO toDto(Recebimento e) {
        if (e == null) return null;

        RecebimentoDTO dto = new RecebimentoDTO();
        dto.setId(e.getId());
        dto.setLancamentoId(e.getLancamento() != null ? e.getLancamento().getId() : null);
        dto.setContaDestinoId(e.getContaDestino() != null ? e.getContaDestino().getId() : null);
        dto.setValor(e.getValor());
        dto.setDataRecebimento(e.getDataRecebimento());
        dto.setObservacao(e.getObservacao());

        return dto;
    }

    public static Recebimento toEntity(RecebimentoDTO dto, Lancamento lancamento, ContaBancaria contaDestino) {
        if (dto == null) return null;

        Recebimento e = new Recebimento();
        e.setId(dto.getId());
        e.setLancamento(lancamento);
        e.setContaDestino(contaDestino);
        e.setValor(dto.getValor());
        e.setDataRecebimento(dto.getDataRecebimento());
        e.setObservacao(dto.getObservacao());

        return e;
    }

    public static void copyToEntity(RecebimentoDTO dto, Recebimento target, Lancamento lancamento, ContaBancaria contaDestino) {
        if (dto == null || target == null) return;

        target.setLancamento(lancamento);
        target.setContaDestino(contaDestino);
        target.setValor(dto.getValor());
        target.setDataRecebimento(dto.getDataRecebimento());
        target.setObservacao(dto.getObservacao());
    }

    public static List<RecebimentoDTO> toDtoList(Collection<Recebimento> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(RecebimentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<RecebimentoDTO> toDtoPage(Page<Recebimento> page) {
        List<RecebimentoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
