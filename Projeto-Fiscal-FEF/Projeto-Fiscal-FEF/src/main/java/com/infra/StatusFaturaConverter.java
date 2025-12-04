package com.infra;

import com.domains.enums.StatusFatura;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusFaturaConverter implements AttributeConverter<StatusFatura, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusFatura status) {
        return status == null ? null : status.getId();
    }

    @Override
    public StatusFatura convertToEntityAttribute(Integer dbValue) {
        return StatusFatura.toEnum(dbValue);
    }
}

