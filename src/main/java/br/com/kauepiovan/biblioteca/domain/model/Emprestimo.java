package br.com.kauepiovan.biblioteca.domain.model;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Emprestimo {
    @Id
    private UUID id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Livro livro;
    @ManyToOne
    private Bibliotecario bibliotecario;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private boolean finalizado;

    @Deprecated
    protected Emprestimo() {
    }

    public Emprestimo(Usuario usuario, Livro livro, Bibliotecario bibliotecario) {
        this.id = UUID.randomUUID();
        this.usuario = usuario;
        this.livro = livro;
        this.bibliotecario = bibliotecario;
        this.dataCriacao = LocalDate.now();
        this.dataVencimento = dataCriacao.plusDays(7);
        this.finalizado = false;
    }

    public UUID getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

}
