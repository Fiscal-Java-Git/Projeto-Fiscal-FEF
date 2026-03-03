package com.domains.dtos;

public class ImpostoResponseDTO {

    private double valor;
    private String tipoTransacao;
    public double imposto;

    public ImpostoResponseDTO(double valor, String tipoTransacao, double imposto) {
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
        this.imposto = imposto;
    }

    public double getValor() {return valor;}
    public String getTipoTransacao() {return tipoTransacao;}
    public double getImposto() {return imposto;}

}
