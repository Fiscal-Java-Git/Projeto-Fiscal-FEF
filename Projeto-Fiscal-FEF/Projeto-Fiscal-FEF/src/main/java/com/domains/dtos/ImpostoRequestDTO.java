package com.domains.dtos;

import com.services.ImpostoService;

public class ImpostoRequestDTO {

    private double valor;
    private String tipoTransacao;

    public double getValor() {return valor;}
    public void setValor(double valor) {this.valor = valor;}

    public  String getTipoTransacao() {return tipoTransacao;}
    public void setTipoTransacao(String tipoTransacao) {this.tipoTransacao = tipoTransacao;}
}
