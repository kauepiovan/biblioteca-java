package com.kauepiovan.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.kauepiovan.biblioteca.exceptions.EmailAlreadyExistException;
import br.com.kauepiovan.biblioteca.repository.impl.BibliotecarioRepositoryImpl;
import br.com.kauepiovan.biblioteca.services.BibliotecarioService;

public class BibliotecarioServiceTest {
    private BibliotecarioRepositoryImpl respository;
    private BibliotecarioService service;

    @BeforeEach
    void setup() {
        respository = new BibliotecarioRepositoryImpl();
        service = new BibliotecarioService(respository);
    }

    @Test
    @DisplayName("Deve cadastrar o bibliotecario")
    void deveCadastrarBibliotecarioSeEmailNaoExistir() throws Exception {
        service.cadastrarBibliotecario("nome", "nome@email.com");
        var bibliotecario = respository.findByEmail("nome@email.com").orElseThrow();

        assertEquals("nome", bibliotecario.getNome());
        assertEquals("nome@email.com", bibliotecario.getEmail());
        assertEquals(1, respository.findAll().size());
    }

    @Test
    @DisplayName("Deve lancar uma excecao se o email do cadastro ja existir")
    void deveLancarExceptionSeEmailExistir() throws Exception {
        service.cadastrarBibliotecario("nome", "nome@email.com");
        assertThrows(EmailAlreadyExistException.class, () -> {
            service.cadastrarBibliotecario("outro nome", "nome@email.com");
        });
    }

    @Test
    @DisplayName("Deve listar todos os bibliotecarios cadastrados")
    void deveListarTodosBibliotecariosCadastrados() throws Exception{
        service.cadastrarBibliotecario("nome 1", "nome1@email.com");
        service.cadastrarBibliotecario("nome 2" , "nome2@email.com");
        service.cadastrarBibliotecario("nome 3", "nome3@email.com");

        assertEquals(3, service.listarBibliotecarios().size());
    }
    
    
}
