package com.domains.enums;

public enum StatusLancamento {

    PENDENTE(0, "PENDENTE"),
    BAIXADO(1, "BAIXADO"),
    PARCIAL(2, "PARCIAL"),
    CANCELADO(3, "CANCELADO");

    private final Integer id;
    private final String descricao;

    StatusLancamento(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() { return id; }
    public String getDescricao() { return descricao; }

    public static StatusLancamento toEnum(Integer id) {
        if (id == null) return null;
        for (StatusLancamento status : StatusLancamento.values()) {
            if (id.equals(status.getId())) return status;
        }
        throw new IllegalArgumentException("StatusLancamento inválido!");
    }
}
