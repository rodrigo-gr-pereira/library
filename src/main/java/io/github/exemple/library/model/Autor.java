package io.github.exemple.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public")
@Getter
@Setter
@ToString
public class Autor {

    @Id
    @Column(name = "id", length = 100, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

   // @OneToMany(mappedBy = "autor")
    @Transient
    private List<Livro> livros;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", livros=" + livros +
                '}';
    }
}
