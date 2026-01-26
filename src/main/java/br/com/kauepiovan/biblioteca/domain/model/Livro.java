package br.com.kauepiovan.biblioteca.domain.model;

import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.domain.enums.StatusLivro;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Livro {
    @Id
    private UUID id;
    private String titulo;
    private String autor;
    private GeneroLiterario categoria;
    private StatusLivro status;

    @Deprecated
    protected Livro() {
    }

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

}
