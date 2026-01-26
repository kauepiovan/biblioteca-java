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
    private jakarta.persistence.EntityManagerFactory emf;
    private jakarta.persistence.EntityManager em;

    @BeforeEach
    void setup() {
        java.util.Map<String, String> properties = new java.util.HashMap<>();
        properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");

        emf = jakarta.persistence.Persistence.createEntityManagerFactory("biblioteca-pu", properties);
        em = emf.createEntityManager();

        respository = new BibliotecarioRepositoryImpl(em);
        service = new BibliotecarioService(respository);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        if (em != null)
            em.close();
        if (emf != null)
            emf.close();
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
    void deveListarTodosBibliotecariosCadastrados() throws Exception {
        service.cadastrarBibliotecario("nome 1", "nome1@email.com");
        service.cadastrarBibliotecario("nome 2", "nome2@email.com");
        service.cadastrarBibliotecario("nome 3", "nome3@email.com");

        assertEquals(3, service.listarBibliotecarios().size());
    }

}
