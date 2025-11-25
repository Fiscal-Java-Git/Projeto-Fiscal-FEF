package domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "centro_custo")
@SequenceGenerator(name = "seq_centro_custo", sequenceName = "seq_centro_custo", allocationSize = 1)
public class CentroCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_centro_custo")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String nome;

    @Size(max = 200)
    @Column(length = 200)
    private String descricao;

    public CentroCusto() {
    }

    public CentroCusto(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CentroCusto)) return false;
        CentroCusto that = (CentroCusto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
