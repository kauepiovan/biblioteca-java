package br.com.kauepiovan.biblioteca.domain.model;

import java.util.UUID;

public class Bibliotecario extends Pessoa{
    private UUID matricula;

    public Bibliotecario(String nome, String email){ 
        super(nome, email);
        this.matricula = UUID.randomUUID();
    }

    public UUID getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return "{\nid=" + getId() + ",\n" +
               "nome=" + getNome() + ",\n" +
               "email=" + getEmail() + ",\n" +
               "matricula=" + getMatricula() + "\n}";
    }
}
