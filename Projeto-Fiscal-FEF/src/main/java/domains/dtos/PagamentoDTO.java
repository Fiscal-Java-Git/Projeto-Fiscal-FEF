package domains.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class PagamentoDTO {

    private Long id;

    @NotNull
    private Long lancamentoId;

    @NotNull
    private LocalDate dataPagamento;

    @NotNull
    @PositiveOrZero
    private Double valorPago;

    public PagamentoDTO() {
    }

    public PagamentoDTO(Long id, Long lancamentoId, LocalDate dataPagamento, Double valorPago) {
        this.id = id;
        this.lancamentoId = lancamentoId;
        this.dataPagamento = dataPagamento;
        this.valorPago = valorPago;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLancamentoId() { return lancamentoId; }
    public void setLancamentoId(Long lancamentoId) { this.lancamentoId = lancamentoId; }

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }

    public Double getValorPago() { return valorPago; }
    public void setValorPago(Double valorPago) { this.valorPago = valorPago; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PagamentoDTO)) return false;
        PagamentoDTO that = (PagamentoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
