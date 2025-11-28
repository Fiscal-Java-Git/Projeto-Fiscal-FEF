package com.projetofef.domains.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.util.Objects;

public class MovimentoContaDTO {

    private Long id;

    @NotNull
    private Long contaBancariaId;

    @NotNull
    private LocalDate dataMovimento;

    @NotNull
    @PositiveOrZero
    private Double valor;

    @NotNull
    private Integer tipoMovimento; // enum TipoMovimento

    public MovimentoContaDTO() {
    }

    public MovimentoContaDTO(Long id, Long contaBancariaId, LocalDate dataMovimento, Double valor, Integer tipoMovimento) {
        this.id = id;
        this.contaBancariaId = contaBancariaId;
        this.dataMovimento = dataMovimento;
        this.valor = valor;
        this.tipoMovimento = tipoMovimento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getContaBancariaId() { return contaBancariaId; }
    public void setContaBancariaId(Long contaBancariaId) { this.contaBancariaId = contaBancariaId; }

    public LocalDate getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(LocalDate dataMovimento) { this.dataMovimento = dataMovimento; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Integer getTipoMovimento() { return tipoMovimento; }
    public void setTipoMovimento(Integer tipoMovimento) { this.tipoMovimento = tipoMovimento; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovimentoContaDTO)) return false;
        MovimentoContaDTO that = (MovimentoContaDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
