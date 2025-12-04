package com.domains.dtos;

import jakarta.validation.constraints.*;

public class CartaoCreditoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "Id deve ser omitido na criação")
    @NotNull(groups = Update.class, message = "Id é obrigatório na atualização")
    private Long id;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "Bandeira é obrigatória")
    private String bandeira;

    @NotBlank(message = "Emissor é obrigatório")
    private String emissor;

    private String apelido;

    @NotNull(message = "Dia de fechamento é obrigatório")
    @Min(1)
    @Max(31)
    private Integer fechamentoFaturaDia;

    @NotNull(message = "Dia de vencimento é obrigatório")
    @Min(1)
    @Max(31)
    private Integer vencimentoFaturaDia;

    private Boolean ativo = true;

    public CartaoCreditoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

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
}
