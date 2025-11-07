package com.projetofef.infra;

import com.projetofef.domains.enums.TipoLancamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class TipoLancamentoConverter implements AttributeConverter<TipoLancamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoLancamento tipo) {
        return tipo == null ? null : tipo.getId();
    }

    @Override
    public TipoLancamento convertToEntityAttribute(Integer dbValue) {
        return TipoLancamento.toEnum(dbValue);
    }
}
