package br.com.kauepiovan.biblioteca.services;

import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.domain.model.Livro;
import br.com.kauepiovan.biblioteca.repository.LivroRepository;

public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public void cadastrarLivro(String titulo, String autor, GeneroLiterario categoria) {
        var livro = new Livro(titulo, autor, categoria);
        repository.addOne(livro);
        System.out.println("Livro: " + livro + " Cadastrado com sucesso!");
    }

    public void listarLivros() {
        var livros = repository.getAll();
        System.out.println("\n=== Lista de Livros ===");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        for (var livro : livros) {
            System.out.println("- Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor() + " | Categoria: " + livro.getCategoria() + " | Status: " + livro.getStatus());
        }
    }

}