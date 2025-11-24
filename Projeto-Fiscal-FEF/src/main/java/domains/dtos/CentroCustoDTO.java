package domains.dtos;

import jakarta.validation.constraints.*;
import java.util.Objects;

public class CentroCustoDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String descricao;

    @NotNull
    private Long usuarioId;

    public CentroCustoDTO() {
    }

    public CentroCustoDTO(Long id, String descricao, Long usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CentroCustoDTO)) return false;
        CentroCustoDTO that = (CentroCustoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
