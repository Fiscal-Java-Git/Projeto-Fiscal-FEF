package domains.dtos;

import jakarta.validation.constraints.*;
import java.util.Objects;

public class EntidadeDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Size(max = 20)
    private String documento;

    public EntidadeDTO() {
    }

    public EntidadeDTO(Long id, String nome, String documento) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntidadeDTO)) return false;
        EntidadeDTO that = (EntidadeDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
