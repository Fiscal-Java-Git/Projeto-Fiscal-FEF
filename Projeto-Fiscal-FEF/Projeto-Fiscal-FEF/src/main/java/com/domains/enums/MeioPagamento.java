package com.domains.enums;

public enum MeioPagamento {

    CONTA(0, "CONTA"),
    CARTAO(1, "CARTAO"),
    DINHEIRO(2, "DINHEIRO"),
    PIX(3, "PIX");

    private final Integer id;
    private final String descricao;

    MeioPagamento(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() { return id; }
    public String getDescricao() { return descricao; }

    public static MeioPagamento toEnum(Integer id) {
        if (id == null) return null;
        for (MeioPagamento meio : MeioPagamento.values()) {
            if (id.equals(meio.getId())) return meio;
        }
        throw new IllegalArgumentException("MeioPagamento inválido!");
    }
}
