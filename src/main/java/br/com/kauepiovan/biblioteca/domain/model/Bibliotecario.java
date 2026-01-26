package br.com.kauepiovan.biblioteca.domain.model;

import jakarta.persistence.Entity;

@Entity
public class Bibliotecario extends Pessoa {

    @Deprecated
    protected Bibliotecario() {
    }

    public Bibliotecario(String nome, String email) {
        super(nome, email);
    }

}
