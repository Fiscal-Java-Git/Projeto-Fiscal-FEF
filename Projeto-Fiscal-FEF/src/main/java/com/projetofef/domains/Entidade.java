package com.projetofef.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "entidade")
@SequenceGenerator(name = "seq_entidade", sequenceName = "seq_entidade", allocationSize = 1)
public class Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_entidade")
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(length = 150, nullable = false)
    private String nome;

    @Size(max = 20)
    @Column(length = 20)
    private String documento;

    @Size(max = 100)
    @Column(length = 100)
    private String email;

    @Size(max = 15)
    @Column(length = 15)
    private String telefone;

    public Entidade() {
    }

    public Entidade(Long id, String nome, String documento, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.telefone = telefone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entidade)) return false;
        Entidade entidade = (Entidade) o;
        return Objects.equals(id, entidade.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
