package com.domains.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PagamentoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotNull(message = "Lançamento é obrigatório")
    private Long lancamentoId;

    @NotNull(message = "Data de pagamento é obrigatória")
    private LocalDate dataPagamento;

    @NotNull(message = "Valor pago é obrigatório")
    private Double valorPago;

    @NotNull(message = "Conta de origem é obrigatória")
    private Long contaOrigemId;

    private String observacao;

    public PagamentoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getLancamentoId() { return lancamentoId; }
    public void setLancamentoId(Long lancamentoId) { this.lancamentoId = lancamentoId; }
    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }
    public Double getValorPago() { return valorPago; }
    public void setValorPago(Double valorPago) { this.valorPago = valorPago; }
    public Long getContaOrigemId() { return contaOrigemId; }
    public void setContaOrigemId(Long contaOrigemId) { this.contaOrigemId = contaOrigemId; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

}
