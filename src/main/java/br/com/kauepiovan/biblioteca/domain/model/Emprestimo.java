package br.com.kauepiovan.biblioteca.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public class Emprestimo {
    private UUID id;
    private Usuario usuario;
    private Livro livro;
    private Bibliotecario bibliotecario;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private boolean finalizado;

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

    @Override
    public String toString() {
        return "{\nid=" + getId() + ",\n" + 
               "usuario=" + getUsuario() + ",\n" + 
               "livro=" + getLivro() + ",\n" + 
               "bibliotecario=" + getBibliotecario() + ",\n" + 
               "data_criacao=" + getDataCriacao() + ",\n" + 
               "data_vencimento=" + getDataVencimento() + ",\n" + 
               "finalizado=" + getFinalizado() + ",\n}";
    }
}
