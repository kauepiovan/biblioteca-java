package br.com.kauepiovan.biblioteca.domain.model;

import java.util.UUID;

public abstract class Pessoa {
    private UUID id;
    private String nome;
    private String email;

    Pessoa(String nome, String email) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }
    
}

