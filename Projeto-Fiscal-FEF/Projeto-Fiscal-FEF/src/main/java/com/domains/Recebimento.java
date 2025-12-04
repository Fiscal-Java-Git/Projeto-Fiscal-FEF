package com.domains;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "recebimento")
@SequenceGenerator(name = "seq_recebimento", sequenceName = "seq_recebimento", allocationSize = 1)
public class Recebimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_recebimento")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lancamento_id", nullable = false)
    private Lancamento lancamento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "conta_destino_id", nullable = false)
    private ContaBancaria contaDestino;

    @Column(name = "data_recebimento", nullable = false)
    private LocalDate dataRecebimento;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(length = 255)
    private String observacao;

    public Recebimento() {}

    public Recebimento(Lancamento lancamento, ContaBancaria contaDestino,
                       LocalDate dataRecebimento, BigDecimal valor, String observacao) {
        this.lancamento = lancamento;
        this.contaDestino = contaDestino;
        this.dataRecebimento = dataRecebimento;
        this.valor = valor;
        this.observacao = observacao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Lancamento getLancamento() { return lancamento; }
    public void setLancamento(Lancamento lancamento) { this.lancamento = lancamento; }

    public ContaBancaria getContaDestino() { return contaDestino; }
    public void setContaDestino(ContaBancaria contaDestino) { this.contaDestino = contaDestino; }

    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recebimento)) return false;
        Recebimento that = (Recebimento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
