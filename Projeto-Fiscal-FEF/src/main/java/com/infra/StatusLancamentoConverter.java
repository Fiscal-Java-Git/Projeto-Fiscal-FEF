package com.infra;

import com.projetofef.domains.enums.StatusLancamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusLancamentoConverter implements AttributeConverter<StatusLancamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusLancamento status) {
        return status == null ? null : status.getId();
    }

    @Override
    public StatusLancamento convertToEntityAttribute(Integer dbValue) {
        return StatusLancamento.toEnum(dbValue);
    }
}
