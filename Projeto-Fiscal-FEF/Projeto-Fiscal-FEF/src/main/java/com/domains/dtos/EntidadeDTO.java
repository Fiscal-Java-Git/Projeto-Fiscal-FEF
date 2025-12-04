package com.domains.dtos;

import jakarta.validation.constraints.*;

public class EntidadeDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "Id deve ser omitido na criação")
    @NotNull(groups = Update.class, message = "Id é obrigatório na atualização")
    private Long id;

    @NotNull(message = "Id do usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Documento é obrigatório")
    private String documento;

    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    private Boolean ativo;

    public EntidadeDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
