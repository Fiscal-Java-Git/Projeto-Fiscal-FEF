package com.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "conta_bancaria")
@SequenceGenerator(name = "seq_conta_bancaria", sequenceName = "seq_conta_bancaria", allocationSize = 1)
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_bancaria")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String instituicao;

    @NotBlank
    @Column(nullable = false, length = 10)
    private String agencia;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String numero;

    @Column(length = 50)
    private String apelido;

    @NotNull
    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldoInicial = BigDecimal.ZERO;

    @NotNull
    @Column(name = "data_saldo_inicial", nullable = false)
    private LocalDateTime dataSaldoInicial = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean ativa = true;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimentoConta> movimentos;

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

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public BigDecimal getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(BigDecimal saldoInicial) { this.saldoInicial = saldoInicial; }

    public LocalDateTime getDataSaldoInicial() { return dataSaldoInicial; }
    public void setDataSaldoInicial(LocalDateTime dataSaldoInicial) { this.dataSaldoInicial = dataSaldoInicial; }

    public Boolean getAtiva() { return ativa; }
    public void setAtiva(Boolean ativa) { this.ativa = ativa; }

    public List<MovimentoConta> getMovimentos() { return movimentos; }
    public void setMovimentos(List<MovimentoConta> movimentos) { this.movimentos = movimentos; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
}
