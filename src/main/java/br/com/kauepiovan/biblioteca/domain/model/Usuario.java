package br.com.kauepiovan.biblioteca.domain.model;

import java.util.ArrayList;
import java.util.List;

import br.com.kauepiovan.biblioteca.domain.enums.TipoUsuario;

public class Usuario extends Pessoa {
    private TipoUsuario tipo;
    private List<Livro> livrosEmprestados = new ArrayList<>();
    private int limiteLivros;

    public Usuario(String nome, String email, TipoUsuario tipo) {
        super(nome, email);
        this.tipo = tipo;
        this.limiteLivros = tipo == TipoUsuario.COMUM ? 3 : 5;
    }

    public void criarEmprestimo(Livro livro) {
        this.livrosEmprestados.add(livro);
    }
    
    public void setLivrosEmprestados(List<Livro> livrosEmprestados) {
        this.livrosEmprestados = livrosEmprestados;
    }
    
    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }
    
    public int getLimiteLivros() {
        return limiteLivros;
    }
    
    public TipoUsuario getTipo() {
        return tipo;
    }
    
}
