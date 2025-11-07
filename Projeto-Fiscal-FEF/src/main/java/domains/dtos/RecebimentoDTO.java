package domains.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class RecebimentoDTO {

    private Long id;

    @NotNull
    private Long lancamentoId;

    @NotNull
    private LocalDate dataRecebimento;

    @NotNull
    @PositiveOrZero
    private Double valorRecebido;

    public RecebimentoDTO() {
    }

    public RecebimentoDTO(Long id, Long lancamentoId, LocalDate dataRecebimento, Double valorRecebido) {
        this.id = id;
        this.lancamentoId = lancamentoId;
        this.dataRecebimento = dataRecebimento;
        this.valorRecebido = valorRecebido;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLancamentoId() { return lancamentoId; }
    public void setLancamentoId(Long lancamentoId) { this.lancamentoId = lancamentoId; }

    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }

    public Double getValorRecebido() { return valorRecebido; }
    public void setValorRecebido(Double valorRecebido) { this.valorRecebido = valorRecebido; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecebimentoDTO)) return false;
        RecebimentoDTO that = (RecebimentoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
