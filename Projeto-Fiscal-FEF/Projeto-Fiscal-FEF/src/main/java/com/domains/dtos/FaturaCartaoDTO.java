package com.domains.dtos;

import com.domains.enums.StatusFatura;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FaturaCartaoDTO {

    private Long id;
    private Long cartaoId;

    private LocalDate competencia;
    private LocalDateTime dataFechamento;
    private LocalDateTime dataVencimento;

    private BigDecimal valorTotal;

    private StatusFatura status;

    private LocalDate dataPagamento;

    // ----------------------------------------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCartaoId() { return cartaoId; }
    public void setCartaoId(Long cartaoId) { this.cartaoId = cartaoId; }

    public LocalDate getCompetencia() { return competencia; }
    public void setCompetencia(LocalDate competencia) { this.competencia = competencia; }

    public LocalDateTime getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDateTime dataFechamento) { this.dataFechamento = dataFechamento; }

    public LocalDateTime getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDateTime dataVencimento) { this.dataVencimento = dataVencimento; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public StatusFatura getStatus() { return status; }
    public void setStatus(StatusFatura status) { this.status = status; }

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }
}
