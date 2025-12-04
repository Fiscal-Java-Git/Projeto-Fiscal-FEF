package com.domains.dtos;

import com.domains.enums.TipoTransacao;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimentoContaDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "Id deve ser omitido na criação")
    @NotNull(groups = Update.class, message = "Id é obrigatório na atualização")
    private Long id;

    @NotNull(message = "Conta é obrigatória")
    private Long contaId;

    @NotNull(message = "Tipo de transação é obrigatório")
    private TipoTransacao tipo;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    private String historico;

    private Long referenciaId;

    private String referenciaTipo;

    @NotNull(message = "Data do movimento é obrigatória")
    private LocalDateTime dataMovimento;

    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public MovimentoContaDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getContaId() { return contaId; }
    public void setContaId(Long contaId) { this.contaId = contaId; }

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
