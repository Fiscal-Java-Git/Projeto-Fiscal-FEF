package com.domains.dtos;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferenciaDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "Conta de origem é obrigatória")
    private Long contaOrigemId;

    @NotNull(message = "Conta de destino é obrigatória")
    private Long contaDestinoId;

    @NotNull(message = "Data é obrigatória")
    private LocalDateTime data;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    private String observacao;

    public TransferenciaDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getContaOrigemId() { return contaOrigemId; }
    public void setContaOrigemId(Long contaOrigemId) { this.contaOrigemId = contaOrigemId; }

    public Long getContaDestinoId() { return contaDestinoId; }
    public void setContaDestinoId(Long contaDestinoId) { this.contaDestinoId = contaDestinoId; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
