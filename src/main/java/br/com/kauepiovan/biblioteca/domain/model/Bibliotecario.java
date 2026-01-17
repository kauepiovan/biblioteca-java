package br.com.kauepiovan.biblioteca.domain.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bibliotecario that = (Bibliotecario) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
