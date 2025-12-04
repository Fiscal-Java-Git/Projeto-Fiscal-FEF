package com.domains.dtos;

import com.domains.enums.MeioPagamento;
import com.domains.enums.StatusLancamento;
import com.domains.enums.TipoLancamento;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LancamentoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "Id deve ser omitido na criação")
    @NotNull(groups = Update.class, message = "Id é obrigatório na atualização")
    private Long id;

    @NotNull(message = "Tipo de lançamento é obrigatório")
    private TipoLancamento tipo;

    private String descricao;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    private Long entidadeId;
    private Long centroCustoId;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    private LocalDateTime dataCompetencia;
    private LocalDateTime dataVencimento;

    private MeioPagamento meioPagamento;

    private Long contaBancariaId;
    private Long cartaoCreditoId;

    private StatusLancamento status;
    private BigDecimal valorBaixado;

    public LancamentoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoLancamento getTipo() { return tipo; }
    public void setTipo(TipoLancamento tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getEntidadeId() { return entidadeId; }
    public void setEntidadeId(Long entidadeId) { this.entidadeId = entidadeId; }

    public Long getCentroCustoId() { return centroCustoId; }
    public void setCentroCustoId(Long centroCustoId) { this.centroCustoId = centroCustoId; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDateTime getDataCompetencia() { return dataCompetencia; }
    public void setDataCompetencia(LocalDateTime dataCompetencia) { this.dataCompetencia = dataCompetencia; }

    public LocalDateTime getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDateTime dataVencimento) { this.dataVencimento = dataVencimento; }

    public MeioPagamento getMeioPagamento() { return meioPagamento; }
    public void setMeioPagamento(MeioPagamento meioPagamento) { this.meioPagamento = meioPagamento; }

    public Long getContaBancariaId() { return contaBancariaId; }
    public void setContaBancariaId(Long contaBancariaId) { this.contaBancariaId = contaBancariaId; }

    public Long getCartaoCreditoId() { return cartaoCreditoId; }
    public void setCartaoCreditoId(Long cartaoCreditoId) { this.cartaoCreditoId = cartaoCreditoId; }

    public StatusLancamento getStatus() { return status; }
    public void setStatus(StatusLancamento status) { this.status = status; }

    public BigDecimal getValorBaixado() { return valorBaixado; }
    public void setValorBaixado(BigDecimal valorBaixado) { this.valorBaixado = valorBaixado; }
}
