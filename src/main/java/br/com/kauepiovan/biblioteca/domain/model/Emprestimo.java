package br.com.kauepiovan.biblioteca.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public class Emprestimo {
    private UUID id;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private boolean finalizado;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.id = UUID.randomUUID();
        this.usuario = usuario;
        this.livro = livro;
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
               "data_criacao=" + getDataCriacao() + ",\n" + 
               "data_vencimento=" + getDataVencimento() + ",\n" + 
               "finalizado=" + getFinalizado() + ",\n}";
    }
}
