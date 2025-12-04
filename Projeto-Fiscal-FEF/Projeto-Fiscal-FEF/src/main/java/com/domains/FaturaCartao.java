package com.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.domains.enums.StatusFatura;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fatura_cartao", uniqueConstraints = @UniqueConstraint(columnNames = {"cartao_id", "competencia"}))
@SequenceGenerator(name = "seq_fatura", sequenceName = "seq_fatura", allocationSize = 1)
public class FaturaCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fatura")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cartao_id", nullable = false)
    private CartaoCredito cartao;

    @NotNull
    private LocalDate competencia;

    private LocalDateTime dataFechamento;
    private LocalDateTime dataVencimento;

    @NotNull
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private StatusFatura status = StatusFatura.ABERTA;

    @OneToMany(mappedBy = "faturaCartao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lancamento> lancamentos;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime criadoEm = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    private LocalDate dataPagamento;

    @PrePersist
    public void prePersist() {
        this.criadoEm = this.atualizadoEm = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartaoCredito getCartao() {
        return cartao;
    }

    public void setCartao(CartaoCredito cartao) {
        this.cartao = cartao;
    }

    public LocalDate getCompetencia() {
        return competencia;
    }

    public void setCompetencia(LocalDate competencia) {
        this.competencia = competencia;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusFatura getStatus() {
        return status;
    }

    public void setStatus(StatusFatura status) {
        this.status = status;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FaturaCartao that = (FaturaCartao) o;
        return Objects.equals(id, that.id) && Objects.equals(cartao, that.cartao) && Objects.equals(competencia, that.competencia) && Objects.equals(dataFechamento, that.dataFechamento) && Objects.equals(dataVencimento, that.dataVencimento) && Objects.equals(valorTotal, that.valorTotal) && status == that.status && Objects.equals(lancamentos, that.lancamentos) && Objects.equals(criadoEm, that.criadoEm) && Objects.equals(atualizadoEm, that.atualizadoEm) && Objects.equals(dataPagamento, that.dataPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartao, competencia, dataFechamento, dataVencimento, valorTotal, status, lancamentos, criadoEm, atualizadoEm, dataPagamento);
    }
}
