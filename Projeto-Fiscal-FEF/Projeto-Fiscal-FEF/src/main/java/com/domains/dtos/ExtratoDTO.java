package com.domains.dtos;

import java.math.BigDecimal;
import java.util.List;

public class ExtratoDTO {

    private Long contaId;
    private String instituicao;
    private String agencia;
    private String numero;
    private String apelido;

    private BigDecimal saldoInicial;
    private BigDecimal saldoFinal;

    private List<MovimentoContaDTO> movimentacoes;

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public List<MovimentoContaDTO> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentoContaDTO> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}

