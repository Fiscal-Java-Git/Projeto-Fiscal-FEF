package com.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transferencia")
@SequenceGenerator(name = "seq_transferencia", sequenceName = "seq_transferencia", allocationSize = 1)
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transferencia")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "conta_origem_id", nullable = false)
    private ContaBancaria contaOrigem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "conta_destino_id", nullable = false)
    private ContaBancaria contaDestino;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_transferencia", nullable = false)
    private LocalDateTime data;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(length = 255)
    private String observacao;

    public Transferencia() {}

    public Transferencia(Long id, Usuario usuario, ContaBancaria contaOrigem, ContaBancaria contaDestino,
                         LocalDateTime data, BigDecimal valor, String observacao) {
        this.id = id;
        this.usuario = usuario;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.data = data;
        this.valor = valor;
        this.observacao = observacao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public ContaBancaria getContaOrigem() { return contaOrigem; }
    public void setContaOrigem(ContaBancaria contaOrigem) { this.contaOrigem = contaOrigem; }

    public ContaBancaria getContaDestino() { return contaDestino; }
    public void setContaDestino(ContaBancaria contaDestino) { this.contaDestino = contaDestino; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transferencia)) return false;
        Transferencia that = (Transferencia) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
