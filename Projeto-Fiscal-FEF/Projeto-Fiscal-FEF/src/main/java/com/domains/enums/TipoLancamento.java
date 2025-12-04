package com.domains.enums;

public enum TipoLancamento {

    PAGAR(0, "PAGAR"),
    RECEBER(1, "RECEBER");

    private final Integer id;
    private final String descricao;

    TipoLancamento(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() { return id; }
    public String getDescricao() { return descricao; }

    public static TipoLancamento toEnum(Integer id) {
        if (id == null) return null;
        for (TipoLancamento t : TipoLancamento.values()) {
            if (id.equals(t.getId())) return t;
        }
        throw new IllegalArgumentException("TipoLancamento inválido!");
    }
}
