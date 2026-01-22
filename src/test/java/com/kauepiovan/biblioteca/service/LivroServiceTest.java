package com.kauepiovan.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.domain.model.Livro;
import br.com.kauepiovan.biblioteca.repository.impl.LivroRepositoryImpl;
import br.com.kauepiovan.biblioteca.services.LivroService;

public class LivroServiceTest {
    private LivroRepositoryImpl repository;
    private LivroService service;

    @BeforeEach
    void setup() {
        repository = new LivroRepositoryImpl();
        service = new LivroService(repository);
    }

    @Test
    @DisplayName("Deve cadastrar um livro")
    void deveCadastrarLivro() {
        service.cadastrarLivro("titulo", "autor", GeneroLiterario.FANTASIA);
        Livro livro = repository.findAll().getFirst();

        assertEquals(1, repository.findAll().size());
        assertEquals("titulo", livro.getTitulo());
        assertEquals("autor", livro.getAutor());
        assertEquals(GeneroLiterario.FANTASIA, livro.getCategoria());
    }

    @Test
    @DisplayName("Deve listar os livros cadastrados")
    void deveListarTodosOsLivrosCadastrados() {
        service.cadastrarLivro("titulo - 1", "autor - 1", GeneroLiterario.ACAO_E_AVENTURA);
        service.cadastrarLivro("titulo - 2", "autor - 2", GeneroLiterario.FANTASIA);
        service.cadastrarLivro("titulo - 3", "autor - 3", GeneroLiterario.FICCAO_CIENTIFICA);
        service.cadastrarLivro("titulo - 4", "autor - 4", GeneroLiterario.SUSPENSE);

        assertEquals(4, service.listarLivros().size());
    }
}
