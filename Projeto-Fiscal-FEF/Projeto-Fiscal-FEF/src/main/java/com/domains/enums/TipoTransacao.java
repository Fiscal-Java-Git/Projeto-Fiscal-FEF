package com.domains.enums;

public enum TipoTransacao {

    CREDITO(0, "CREDITO"),
    DEBITO(1, "DEBITO"),
    TRANSFERENCIA(2, "TRANSFERENCIA");

    private Integer id;
    private String descricao;

    TipoTransacao(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public static TipoTransacao toEnum(Integer id) {
        if (id == null) return null;
        for (TipoTransacao tipo : TipoTransacao.values()) {
            if (id.equals(tipo.getId())) return tipo;
        }
        throw new IllegalArgumentException("TipoTransacao inválido!");
    }
}
