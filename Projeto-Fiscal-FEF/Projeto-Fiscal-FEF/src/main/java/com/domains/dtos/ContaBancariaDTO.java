package com.domains.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class ContaBancariaDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "Id deve ser omitido na criação")
    @NotNull(groups = Update.class, message = "Id é obrigatório na atualização")
    private Long id;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "Instituição é obrigatória")
    @Size(max = 100, message = "Instituição deve ter no máximo 100 caracteres")
    private String instituicao;

    @NotBlank(message = "Agência é obrigatória")
    @Size(max = 20, message = "Agência deve ter no máximo 20 caracteres")
    private String agencia;

    @NotBlank(message = "Número da conta é obrigatório")
    @Size(max = 30, message = "Número da conta deve ter no máximo 30 caracteres")
    private String numero;

    private String apelido;

    @NotNull(message = "Saldo inicial é obrigatório")
    private Double saldoInicial;

    private LocalDateTime dataSaldoInicial;

    private Boolean ativa;

    public ContaBancariaDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public Double getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(Double saldoInicial) { this.saldoInicial = saldoInicial; }

    public LocalDateTime getDataSaldoInicial() { return dataSaldoInicial; }
    public void setDataSaldoInicial(LocalDateTime dataSaldoInicial) { this.dataSaldoInicial = dataSaldoInicial; }

    public Boolean getAtiva() { return ativa; }
    public void setAtiva(Boolean ativa) { this.ativa = ativa; }
}
