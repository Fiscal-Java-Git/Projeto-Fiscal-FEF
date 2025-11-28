package com.projetofef.domains.enums;

public enum TipoTransacao {
    CREDITO,
    DEBITO,
    TRANSFERENCIA;

    public String getValue() {
        return name();
    }

    public static TipoTransacao fromValue(String value) {
        if (value == null) return null;
        try {
            return TipoTransacao.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("TipoTransacao inválido: " + value);
        }
    }

    @Override
    public String toString() {
        return getValue();
    }
}
