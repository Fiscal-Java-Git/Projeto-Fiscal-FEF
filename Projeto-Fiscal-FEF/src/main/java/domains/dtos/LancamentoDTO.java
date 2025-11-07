package domains.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class LancamentoDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String descricao;

    @NotNull
    @Positive
    private Double valor;

    @NotNull
    private LocalDate dataLancamento;

    @NotNull
    private Integer tipo; // enum TipoLancamento

    @NotNull
    private Integer status; // enum StatusLancamento

    @NotNull
    private Long contaBancariaId;

    @NotNull
    private Long centroCustoId;

    @NotNull
    private Long entidadeId;

    public LancamentoDTO() {
    }

    public LancamentoDTO(Long id, String descricao, Double valor, LocalDate dataLancamento, Integer tipo, Integer status, Long contaBancariaId, Long centroCustoId, Long entidadeId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataLancamento = dataLancamento;
        this.tipo = tipo;
        this.status = status;
        this.contaBancariaId = contaBancariaId;
        this.centroCustoId = centroCustoId;
        this.entidadeId = entidadeId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }

    public Integer getTipo() { return tipo; }
    public void setTipo(Integer tipo) { this.tipo = tipo; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Long getContaBancariaId() { return contaBancariaId; }
    public void setContaBancariaId(Long contaBancariaId) { this.contaBancariaId = contaBancariaId; }

    public Long getCentroCustoId() { return centroCustoId; }
    public void setCentroCustoId(Long centroCustoId) { this.centroCustoId = centroCustoId; }

    public Long getEntidadeId() { return entidadeId; }
    public void setEntidadeId(Long entidadeId) { this.entidadeId = entidadeId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LancamentoDTO)) return false;
        LancamentoDTO that = (LancamentoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
