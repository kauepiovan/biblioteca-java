package br.com.kauepiovan.biblioteca.domain.model;

import java.util.UUID;

public class Bibliotecario extends Pessoa{
    private UUID id;

    public Bibliotecario(String nome, String email){ 
        super(nome, email);
        this.id = UUID.randomUUID();
    }

    public UUID getMatricula() {
        return id;
    }

    @Override
    public String toString() {
        return "{\nid=" + getId() + ",\n" +
               "nome=" + getNome() + ",\n" +
               "email=" + getEmail() + ",\n" +
               "matricula=" + getMatricula() + "\n}";
    }
}
