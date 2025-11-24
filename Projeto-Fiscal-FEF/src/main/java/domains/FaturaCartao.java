package domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "fatura_cartao")
@SequenceGenerator(name = "seq_fatura_cartao", sequenceName = "seq_fatura_cartao", allocationSize = 1)
public class FaturaCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fatura_cartao")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cartao_credito_id", nullable = false)
    @JsonBackReference
    private CartaoCredito cartaoCredito;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataFechamento;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataVencimento;

    @NotNull
    @Column(nullable = false)
    private Double valorTotal = 0.0;

    @NotNull
    @Column(nullable = false)
    private Double valorPago = 0.0;

    public FaturaCartao() {
    }

    public FaturaCartao(Long id, CartaoCredito cartaoCredito, LocalDate dataFechamento, LocalDate dataVencimento, Double valorTotal, Double valorPago) {
        this.id = id;
        this.cartaoCredito = cartaoCredito;
        this.dataFechamento = dataFechamento;
        this.dataVencimento = dataVencimento;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CartaoCredito getCartaoCredito() { return cartaoCredito; }
    public void setCartaoCredito(CartaoCredito cartaoCredito) { this.cartaoCredito = cartaoCredito; }

    public LocalDate getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDate dataFechamento) { this.dataFechamento = dataFechamento; }

    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public Double getValorPago() { return valorPago; }
    public void setValorPago(Double valorPago) { this.valorPago = valorPago; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FaturaCartao)) return false;
        FaturaCartao that = (FaturaCartao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
