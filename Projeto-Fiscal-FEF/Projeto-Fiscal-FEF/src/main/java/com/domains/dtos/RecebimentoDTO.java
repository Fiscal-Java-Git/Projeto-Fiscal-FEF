package com.domains.dtos;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RecebimentoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotNull(message = "Lançamento é obrigatório")
    private Long lancamentoId;

    @NotNull(message = "Conta de destino é obrigatória")
    private Long contaDestinoId;

    @NotNull(message = "Data de recebimento é obrigatória")
    private LocalDate dataRecebimento;

    @NotNull(message = "Valor do recebimento é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    private String observacao;

    public RecebimentoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLancamentoId() { return lancamentoId; }
    public void setLancamentoId(Long lancamentoId) { this.lancamentoId = lancamentoId; }

    public Long getContaDestinoId() { return contaDestinoId; }
    public void setContaDestinoId(Long contaDestinoId) { this.contaDestinoId = contaDestinoId; }

    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
