package domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "conta_bancaria")
@SequenceGenerator(name = "seq_conta_bancaria", sequenceName = "seq_conta_bancaria", allocationSize = 1)
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_bancaria")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String instituicao;

    @Size(max = 10)
    @Column(length = 10)
    private String agencia;

    @Size(max = 20)
    @Column(length = 20)
    private String numero;

    @Size(max = 50)
    @Column(length = 50)
    private String apelido;

    @NotNull
    @Column(nullable = false)
    private Double saldoInicial;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_saldo_inicial")
    private LocalDate dataSaldoInicial;

    @Column(nullable = false)
    private Boolean ativa = true;

    public ContaBancaria() {
    }

    public ContaBancaria(Long id, Usuario usuario, String instituicao, String agencia, String numero, String apelido, Double saldoInicial, LocalDate dataSaldoInicial, Boolean ativa) {
        this.id = id;
        this.usuario = usuario;
        this.instituicao = instituicao;
        this.agencia = agencia;
        this.numero = numero;
        this.apelido = apelido;
        this.saldoInicial = saldoInicial;
        this.dataSaldoInicial = dataSaldoInicial;
        this.ativa = ativa;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public Double getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(Double saldoInicial) { this.saldoInicial = saldoInicial; }

    public LocalDate getDataSaldoInicial() { return dataSaldoInicial; }
    public void setDataSaldoInicial(LocalDate dataSaldoInicial) { this.dataSaldoInicial = dataSaldoInicial; }

    public Boolean getAtiva() { return ativa; }
    public void setAtiva(Boolean ativa) { this.ativa = ativa; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaBancaria)) return false;
        ContaBancaria that = (ContaBancaria) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
