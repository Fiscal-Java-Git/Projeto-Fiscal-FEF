package com.projetofef.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "cartao_credito")
@SequenceGenerator(name = "seq_cartao_credito", sequenceName = "seq_cartao_credito", allocationSize = 1)
public class CartaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cartao_credito")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String nome;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String instituicao;

    @NotNull
    @Column(nullable = false)
    private Integer diaFechamento;

    @NotNull
    @Column(nullable = false)
    private Integer diaVencimento;

    @NotNull
    @Column(nullable = false)
    private Double limiteTotal;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

    public CartaoCredito() {
    }

    public CartaoCredito(Long id, Usuario usuario, String nome, String instituicao, Integer diaFechamento, Integer diaVencimento, Double limiteTotal, Boolean ativo) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.instituicao = instituicao;
        this.diaFechamento = diaFechamento;
        this.diaVencimento = diaVencimento;
        this.limiteTotal = limiteTotal;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public Integer getDiaFechamento() { return diaFechamento; }
    public void setDiaFechamento(Integer diaFechamento) { this.diaFechamento = diaFechamento; }

    public Integer getDiaVencimento() { return diaVencimento; }
    public void setDiaVencimento(Integer diaVencimento) { this.diaVencimento = diaVencimento; }

    public Double getLimiteTotal() { return limiteTotal; }
    public void setLimiteTotal(Double limiteTotal) { this.limiteTotal = limiteTotal; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaoCredito)) return false;
        CartaoCredito that = (CartaoCredito) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
