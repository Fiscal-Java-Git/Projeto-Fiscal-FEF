package domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import domains.enums.StatusLancamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "recebimento")
@SequenceGenerator(name = "seq_recebimento", sequenceName = "seq_recebimento", allocationSize = 1)
public class Recebimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_recebimento")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lancamento_id", nullable = false)
    @JsonBackReference
    private Lancamento lancamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_bancaria_id", nullable = false)
    @JsonBackReference
    private ContaBancaria contaBancaria;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataRecebimento;

    @NotNull
    @Column(nullable = false)
    private Double valorRecebido;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private StatusLancamento status;

    public Recebimento() {
        this.dataRecebimento = LocalDate.now();
    }

    public Recebimento(Long id, Lancamento lancamento, ContaBancaria contaBancaria, LocalDate dataRecebimento, Double valorRecebido, StatusLancamento status) {
        this.id = id;
        this.lancamento = lancamento;
        this.contaBancaria = contaBancaria;
        this.dataRecebimento = (dataRecebimento == null ? LocalDate.now() : dataRecebimento);
        this.valorRecebido = valorRecebido;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Lancamento getLancamento() { return lancamento; }
    public void setLancamento(Lancamento lancamento) { this.lancamento = lancamento; }

    public ContaBancaria getContaBancaria() { return contaBancaria; }
    public void setContaBancaria(ContaBancaria contaBancaria) { this.contaBancaria = contaBancaria; }

    public LocalDate getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(LocalDate dataRecebimento) { this.dataRecebimento = dataRecebimento; }

    public Double getValorRecebido() { return valorRecebido; }
    public void setValorRecebido(Double valorRecebido) { this.valorRecebido = valorRecebido; }

    public StatusLancamento getStatus() { return status; }
    public void setStatus(StatusLancamento status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recebimento)) return false;
        Recebimento that = (Recebimento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
