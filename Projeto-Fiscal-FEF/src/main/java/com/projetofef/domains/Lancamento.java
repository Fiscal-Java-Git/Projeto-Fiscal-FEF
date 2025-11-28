package com.projetofef.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetofef.domains.enums.TipoLancamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "lancamento")
@SequenceGenerator(name = "seq_lancamento", sequenceName = "seq_lancamento", allocationSize = 1)
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lancamento")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_custo_id")
    @JsonBackReference
    private CentroCusto centroCusto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidade_id")
    @JsonBackReference
    private Entidade entidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TipoLancamento tipo;

    @Size(max = 200)
    @Column(length = 200)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private Double valor;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataLancamento;

    public Lancamento() {
        this.dataLancamento = LocalDate.now();
    }

    public Lancamento(Long id, Usuario usuario, CentroCusto centroCusto, Entidade entidade, TipoLancamento tipo, String descricao, Double valor, LocalDate dataLancamento) {
        this.id = id;
        this.usuario = usuario;
        this.centroCusto = centroCusto;
        this.entidade = entidade;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.dataLancamento = (dataLancamento == null ? LocalDate.now() : dataLancamento);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public CentroCusto getCentroCusto() { return centroCusto; }
    public void setCentroCusto(CentroCusto centroCusto) { this.centroCusto = centroCusto; }

    public Entidade getEntidade() { return entidade; }
    public void setEntidade(Entidade entidade) { this.entidade = entidade; }

    public TipoLancamento getTipo() { return tipo; }
    public void setTipo(TipoLancamento tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lancamento)) return false;
        Lancamento that = (Lancamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
