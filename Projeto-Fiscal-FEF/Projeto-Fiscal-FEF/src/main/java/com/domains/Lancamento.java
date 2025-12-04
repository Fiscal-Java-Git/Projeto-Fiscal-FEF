package com.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.domains.enums.MeioPagamento;
import com.domains.enums.StatusLancamento;
import com.domains.enums.TipoLancamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lancamento")
@SequenceGenerator(name = "seq_lancamento", sequenceName = "seq_lancamento", allocationSize = 1)
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lancamento")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoLancamento tipo;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "entidade_id")
    private Entidade entidade;

    @ManyToOne
    @JoinColumn(name = "centro_custo_id")
    private CentroCusto centroCusto;

    @NotNull
    private BigDecimal valor;

    private LocalDateTime dataCompetencia;
    private LocalDateTime dataVencimento;

    @Enumerated(EnumType.STRING)
    private MeioPagamento meioPagamento;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaBancaria contaBancaria;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private CartaoCredito cartaoCredito;

    @ManyToOne
    @JoinColumn(name = "fatura_id")
    private FaturaCartao faturaCartao;

    @Enumerated(EnumType.STRING)
    private StatusLancamento status = StatusLancamento.PENDENTE;

    private BigDecimal valorBaixado = BigDecimal.ZERO;

    @OneToMany(mappedBy = "lancamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> pagamentos;

    @OneToMany(mappedBy = "lancamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recebimento> recebimentos;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime criadoEm = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    @PrePersist
    public void prePersist() { this.criadoEm = this.atualizadoEm = LocalDateTime.now(); }

    @PreUpdate
    public void preUpdate() { this.atualizadoEm = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public TipoLancamento getTipo() { return tipo; }
    public void setTipo(TipoLancamento tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Entidade getEntidade() { return entidade; }
    public void setEntidade(Entidade entidade) { this.entidade = entidade; }

    public CentroCusto getCentroCusto() { return centroCusto; }
    public void setCentroCusto(CentroCusto centroCusto) { this.centroCusto = centroCusto; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDateTime getDataCompetencia() { return dataCompetencia; }
    public void setDataCompetencia(LocalDateTime dataCompetencia) { this.dataCompetencia = dataCompetencia; }

    public LocalDateTime getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDateTime dataVencimento) { this.dataVencimento = dataVencimento; }

    public MeioPagamento getMeioPagamento() { return meioPagamento; }
    public void setMeioPagamento(MeioPagamento meioPagamento) { this.meioPagamento = meioPagamento; }

    public ContaBancaria getContaBancaria() { return contaBancaria; }
    public void setContaBancaria(ContaBancaria contaBancaria) { this.contaBancaria = contaBancaria; }

    public CartaoCredito getCartaoCredito() { return cartaoCredito; }
    public void setCartaoCredito(CartaoCredito cartaoCredito) { this.cartaoCredito = cartaoCredito; }

    public FaturaCartao getFaturaCartao() { return faturaCartao; }
    public void setFaturaCartao(FaturaCartao faturaCartao) { this.faturaCartao = faturaCartao; }

    public StatusLancamento getStatus() { return status; }
    public void setStatus(StatusLancamento status) { this.status = status; }

    public BigDecimal getValorBaixado() { return valorBaixado; }
    public void setValorBaixado(BigDecimal valorBaixado) { this.valorBaixado = valorBaixado; }

    public List<Pagamento> getPagamentos() { return pagamentos; }
    public void setPagamentos(List<Pagamento> pagamentos) { this.pagamentos = pagamentos; }

    public List<Recebimento> getRecebimentos() { return recebimentos; }
    public void setRecebimentos(List<Recebimento> recebimentos) { this.recebimentos = recebimentos; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
}
