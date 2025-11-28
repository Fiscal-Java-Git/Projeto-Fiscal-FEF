package com.projetofef.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetofef.domains.enums.TipoMovimento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "movimento_conta")
@SequenceGenerator(name = "seq_movimento_conta", sequenceName = "seq_movimento_conta", allocationSize = 1)
public class MovimentoConta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movimento_conta")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_bancaria_id", nullable = false)
    @JsonBackReference
    private ContaBancaria contaBancaria;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TipoMovimento tipo;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataMovimento;

    @NotNull
    @Column(nullable = false)
    private Double valor;

    @Column(length = 200)
    private String descricao;

    public MovimentoConta() {
        this.dataMovimento = LocalDate.now();
    }

    public MovimentoConta(Long id, ContaBancaria contaBancaria, TipoMovimento tipo, LocalDate dataMovimento, Double valor, String descricao) {
        this.id = id;
        this.contaBancaria = contaBancaria;
        this.tipo = tipo;
        this.dataMovimento = (dataMovimento == null ? LocalDate.now() : dataMovimento);
        this.valor = valor;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ContaBancaria getContaBancaria() { return contaBancaria; }
    public void setContaBancaria(ContaBancaria contaBancaria) { this.contaBancaria = contaBancaria; }

    public TipoMovimento getTipo() { return tipo; }
    public void setTipo(TipoMovimento tipo) { this.tipo = tipo; }

    public LocalDate getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(LocalDate dataMovimento) { this.dataMovimento = dataMovimento; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovimentoConta)) return false;
        MovimentoConta that = (MovimentoConta) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
