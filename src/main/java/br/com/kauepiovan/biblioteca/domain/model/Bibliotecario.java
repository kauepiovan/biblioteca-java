package br.com.kauepiovan.biblioteca.domain.model;

import java.util.UUID;

public class Bibliotecario extends Pessoa{
    public UUID matricula;

    Bibliotecario(String nome, String email){ 
        super(nome, email);
        this.matricula = UUID.randomUUID();
    }
}
