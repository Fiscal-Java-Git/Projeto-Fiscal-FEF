package com.domains.dtos;


import java.math.BigDecimal;

public class PosicaoGeralDTO {

    private BigDecimal totalReceber;
    private BigDecimal totalPagar;
    private BigDecimal saldo;

    public PosicaoGeralDTO(BigDecimal totalReceber,
                           BigDecimal totalPagar,
                           BigDecimal saldo) {
        this.totalReceber = totalReceber;
        this.totalPagar = totalPagar;
        this.saldo = saldo;
    }

    public BigDecimal getTotalReceber() {
        return totalReceber;
    }

    public BigDecimal getTotalPagar() {
        return totalPagar;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
