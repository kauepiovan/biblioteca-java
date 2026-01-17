package br.com.kauepiovan.biblioteca.domain.model;

import java.util.UUID;
import java.util.Objects;

import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.domain.enums.StatusLivro;

public class Livro {
    private UUID id;
    private String titulo;
    private String autor;
    private GeneroLiterario categoria;
    private StatusLivro status;

    public Livro(String titulo, String autor, GeneroLiterario categoria) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.status = StatusLivro.DISPONIVEL;
    }

    public UUID getId() {
        return id;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public GeneroLiterario getCategoria() {
        return categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setStatus(StatusLivro status) {
        this.status = status;
    }

    // @Override
    // public String toString() {
    //     return "Livro={ " +
    //             "id=" + getId() +
    //             ", titulo='" + getTitulo() + '\'' +
    //             ", autor='" + getAutor() + '\'' +
    //             ", categoria=" + getCategoria() +
    //             ", status=" + getStatus() +
    //             " }";
    // }

    @Override
    public String toString() {
        return "{\nid=" + getId() + ",\n" +
               "titulo=" + getTitulo() + ",\n" +
               "autor=" + getAutor() + ",\n" +
               "categoria=" + getCategoria() + ",\n" +
               "status=" + getStatus() + "\n}" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
