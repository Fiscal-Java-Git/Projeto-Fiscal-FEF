package com.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cartao_credito")
@SequenceGenerator(name = "seq_cartao", sequenceName = "seq_cartao", allocationSize = 1)
public class CartaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cartao")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotBlank
    private String bandeira;

    @NotBlank
    private String emissor;

    private String apelido;

    private Integer fechamentoFaturaDia;
    private Integer vencimentoFaturaDia;

    private Boolean ativo = true;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FaturaCartao> faturas;

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

    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }

    public String getEmissor() { return emissor; }
    public void setEmissor(String emissor) { this.emissor = emissor; }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public Integer getFechamentoFaturaDia() { return fechamentoFaturaDia; }
    public void setFechamentoFaturaDia(Integer fechamentoFaturaDia) { this.fechamentoFaturaDia = fechamentoFaturaDia; }

    public Integer getVencimentoFaturaDia() { return vencimentoFaturaDia; }
    public void setVencimentoFaturaDia(Integer vencimentoFaturaDia) { this.vencimentoFaturaDia = vencimentoFaturaDia; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public List<FaturaCartao> getFaturas() { return faturas; }
    public void setFaturas(List<FaturaCartao> faturas) { this.faturas = faturas; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
}
