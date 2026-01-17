package br.com.kauepiovan.biblioteca.services;

import java.util.List;

import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.domain.model.Livro;
import br.com.kauepiovan.biblioteca.repository.interfaces.LivroRepository;

public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public void cadastrarLivro(String titulo, String autor, GeneroLiterario categoria) {
        var livro = new Livro(titulo, autor, categoria);
        repository.save(livro);
    }

    public List<Livro> listarLivros() {
        return repository.findAll();
    }

}