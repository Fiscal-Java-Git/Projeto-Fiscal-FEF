package com.projetofef.domains.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.util.Objects;

public class FaturaCartaoDTO {

    private Long id;

    @NotNull
    private Long cartaoCreditoId;

    @NotNull
    @PastOrPresent
    private LocalDate dataFechamento;

    @NotNull
    @FutureOrPresent
    private LocalDate dataVencimento;

    @NotNull
    @PositiveOrZero
    private Double valorTotal;

    public FaturaCartaoDTO() {
    }

    public FaturaCartaoDTO(Long id, Long cartaoCreditoId, LocalDate dataFechamento, LocalDate dataVencimento, Double valorTotal) {
        this.id = id;
        this.cartaoCreditoId = cartaoCreditoId;
        this.dataFechamento = dataFechamento;
        this.dataVencimento = dataVencimento;
        this.valorTotal = valorTotal;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCartaoCreditoId() { return cartaoCreditoId; }
    public void setCartaoCreditoId(Long cartaoCreditoId) { this.cartaoCreditoId = cartaoCreditoId; }

    public LocalDate getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDate dataFechamento) { this.dataFechamento = dataFechamento; }

    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FaturaCartaoDTO)) return false;
        FaturaCartaoDTO that = (FaturaCartaoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
