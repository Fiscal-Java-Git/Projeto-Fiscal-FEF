package com.projetofef.domains.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.Objects;

public class TransferenciaDTO {

    private Long id;

    @NotNull
    private Long contaOrigemId;

    @NotNull
    private Long contaDestinoId;

    @NotNull
    @Positive
    private Double valor;

    @NotNull
    private LocalDate dataTransferencia;

    public TransferenciaDTO() {
    }

    public TransferenciaDTO(Long id, Long contaOrigemId, Long contaDestinoId, Double valor, LocalDate dataTransferencia) {
        this.id = id;
        this.contaOrigemId = contaOrigemId;
        this.contaDestinoId = contaDestinoId;
        this.valor = valor;
        this.dataTransferencia = dataTransferencia;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getContaOrigemId() { return contaOrigemId; }
    public void setContaOrigemId(Long contaOrigemId) { this.contaOrigemId = contaOrigemId; }

    public Long getContaDestinoId() { return contaDestinoId; }
    public void setContaDestinoId(Long contaDestinoId) { this.contaDestinoId = contaDestinoId; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public LocalDate getDataTransferencia() { return dataTransferencia; }
    public void setDataTransferencia(LocalDate dataTransferencia) { this.dataTransferencia = dataTransferencia; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferenciaDTO)) return false;
        TransferenciaDTO that = (TransferenciaDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
