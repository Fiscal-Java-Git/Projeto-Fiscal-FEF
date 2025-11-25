package domains.dtos;

import jakarta.validation.constraints.*;
import java.util.Objects;

public class ContaBancariaDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nomeBanco;

    @NotBlank
    @Size(max = 20)
    private String agencia;

    @NotBlank
    @Size(max = 20)
    private String numeroConta;

    @NotNull
    private Long usuarioId;

    public ContaBancariaDTO() {
    }

    public ContaBancariaDTO(Long id, String nomeBanco, String agencia, String numeroConta, Long usuarioId) {
        this.id = id;
        this.nomeBanco = nomeBanco;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.usuarioId = usuarioId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeBanco() { return nomeBanco; }
    public void setNomeBanco(String nomeBanco) { this.nomeBanco = nomeBanco; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumeroConta() { return numeroConta; }
    public void setNumeroConta(String numeroConta) { this.numeroConta = numeroConta; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaBancariaDTO)) return false;
        ContaBancariaDTO that = (ContaBancariaDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
