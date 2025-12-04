package com.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.domains.enums.TipoTransacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimento_conta")
@SequenceGenerator(name = "seq_movimento_conta", sequenceName = "seq_movimento_conta", allocationSize = 1)
public class MovimentoConta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movimento_conta")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "conta_id", nullable = false)
    private ContaBancaria conta;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    @NotNull
    private BigDecimal valor;

    @Column(length = 200)
    private String historico;

    private Long referenciaId;

    @Column(length = 50)
    private String referenciaTipo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_movimento", nullable = false)
    private LocalDateTime dataMovimento = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm = LocalDateTime.now();


    @PrePersist
    public void prePersist() { this.criadoEm = this.atualizadoEm = LocalDateTime.now(); }

    @PreUpdate
    public void preUpdate() { this.atualizadoEm = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ContaBancaria getConta() { return conta; }
    public void setConta(ContaBancaria conta) { this.conta = conta; }

    public TipoTransacao getTipo() { return tipo; }
    public void setTipo(TipoTransacao tipo) { this.tipo = tipo; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getHistorico() { return historico; }
    public void setHistorico(String historico) { this.historico = historico; }

    public Long getReferenciaId() { return referenciaId; }
    public void setReferenciaId(Long referenciaId) { this.referenciaId = referenciaId; }

    public String getReferenciaTipo() { return referenciaTipo; }
    public void setReferenciaTipo(String referenciaTipo) { this.referenciaTipo = referenciaTipo; }

    public LocalDateTime getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(LocalDateTime dataMovimento) { this.dataMovimento = dataMovimento; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
}
