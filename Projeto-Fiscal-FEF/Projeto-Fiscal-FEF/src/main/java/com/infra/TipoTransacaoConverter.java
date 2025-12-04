package com.infra;

import com.domains.enums.TipoTransacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class TipoTransacaoConverter implements AttributeConverter<TipoTransacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoTransacao tipo) {
        return tipo == null ? null : tipo.getId();
    }

    @Override
    public TipoTransacao convertToEntityAttribute(Integer dbValue) {
        return TipoTransacao.toEnum(dbValue);
    }
}
