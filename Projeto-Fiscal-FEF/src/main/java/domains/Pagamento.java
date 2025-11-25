package domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import domains.enums.StatusLancamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pagamento")
@SequenceGenerator(name = "seq_pagamento", sequenceName = "seq_pagamento", allocationSize = 1)
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pagamento")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lancamento_id", nullable = false)
    @JsonBackReference
    private Lancamento lancamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_bancaria_id", nullable = false)
    @JsonBackReference
    private ContaBancaria contaBancaria;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataPagamento;

    @NotNull
    @Column(nullable = false)
    private Double valorPago;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private StatusLancamento status;

    public Pagamento() {
        this.dataPagamento = LocalDate.now();
    }

    public Pagamento(Long id, Lancamento lancamento, ContaBancaria contaBancaria, LocalDate dataPagamento, Double valorPago, StatusLancamento status) {
        this.id = id;
        this.lancamento = lancamento;
        this.contaBancaria = contaBancaria;
        this.dataPagamento = (dataPagamento == null ? LocalDate.now() : dataPagamento);
        this.valorPago = valorPago;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Lancamento getLancamento() { return lancamento; }
    public void setLancamento(Lancamento lancamento) { this.lancamento = lancamento; }

    public ContaBancaria getContaBancaria() { return contaBancaria; }
    public void setContaBancaria(ContaBancaria contaBancaria) { this.contaBancaria = contaBancaria; }

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }

    public Double getValorPago() { return valorPago; }
    public void setValorPago(Double valorPago) { this.valorPago = valorPago; }

    public StatusLancamento getStatus() { return status; }
    public void setStatus(StatusLancamento status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagamento)) return false;
        Pagamento that = (Pagamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
