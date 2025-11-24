package domains.dtos;

import jakarta.validation.constraints.*;
import java.util.Objects;

public class CartaoCreditoDTO {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotNull
    private Double limite;

    @NotNull
    private Long contaBancariaId;

    public CartaoCreditoDTO() {
    }

    public CartaoCreditoDTO(Long id, String nome, Double limite, Long contaBancariaId) {
        this.id = id;
        this.nome = nome;
        this.limite = limite;
        this.contaBancariaId = contaBancariaId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getLimite() { return limite; }
    public void setLimite(Double limite) { this.limite = limite; }

    public Long getContaBancariaId() { return contaBancariaId; }
    public void setContaBancariaId(Long contaBancariaId) { this.contaBancariaId = contaBancariaId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaoCreditoDTO)) return false;
        CartaoCreditoDTO that = (CartaoCreditoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
