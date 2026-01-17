package br.com.kauepiovan.biblioteca.domain.model;


public class Bibliotecario extends Pessoa{

    public Bibliotecario(String nome, String email){ 
        super(nome, email);
    }

    @Override
    public String toString() {
        return "{\nid=" + getId() + ",\n" +
               "nome=" + getNome() + ",\n" +
               "email=" + getEmail() + "\n}";
    }
}
