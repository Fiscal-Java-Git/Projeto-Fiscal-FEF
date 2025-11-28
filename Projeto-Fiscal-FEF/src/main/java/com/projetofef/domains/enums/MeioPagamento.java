package com.projetofef.domains.enums;

public enum MeioPagamento {
    CONTA,
    CARTAO,
    DINHEIRO,
    PIX;

    public String getValue() {
        return name();
    }

    public static MeioPagamento fromValue(String value) {
        if (value == null) return null;
        try {
            return MeioPagamento.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("MeioPagamento inválido: " + value);
        }
    }

    @Override
    public String toString() {
        return getValue();
    }
}
