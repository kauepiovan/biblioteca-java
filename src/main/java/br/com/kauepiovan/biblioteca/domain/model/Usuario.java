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

    Usuario(String nome, String email) {
        super(nome, email);
        this.tipo = TipoUsuario.COMUM;
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
    
    public void devolverEmprestimo(Livro livro) {
        this.livrosEmprestados.remove(livro);
    }
    
    @Override
    public String toString() {
        return "{\nid=" + getId() + ",\n" +
                "nome=" + getNome() + ",\n" +
                "email=" + getEmail() + ",\n" +
                "tipo=" + tipo + ",\n" +
                "limite_de_livros=" + limiteLivros + ",\n" +
                "livros_emprestados=" + livrosEmprestados + "\n}";
    }
}
