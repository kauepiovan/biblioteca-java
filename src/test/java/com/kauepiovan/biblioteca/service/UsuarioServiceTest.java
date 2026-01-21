package com.kauepiovan.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import br.com.kauepiovan.biblioteca.domain.enums.TipoUsuario;
import br.com.kauepiovan.biblioteca.exceptions.EmailAlreadyExistException;
import br.com.kauepiovan.biblioteca.repository.impl.UsuarioRepositoryImpl;
import br.com.kauepiovan.biblioteca.services.UsuarioService;

public class UsuarioServiceTest {

    private UsuarioRepositoryImpl repository;
    private UsuarioService service;

    @BeforeEach
    void setup() {
        repository = new UsuarioRepositoryImpl();
        service = new UsuarioService(repository);

    }

    @Test
    @DisplayName("Deve cadastrar um usuario se o email nao existir")
    void deveCadastrarUsuarioQuandoEmailNaoExiste() throws Exception {
        service.cadastrarUsuario("joe doe", "joe@email.com", TipoUsuario.COMUM);

        var usuario = repository.findByEmail("joe@email.com").orElseThrow();
        
        assertEquals(usuario.getNome(), "joe doe");
        assertEquals(usuario.getEmail(), "joe@email.com");
        assertEquals(usuario.getTipo(), TipoUsuario.COMUM);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    @DisplayName("Deve lancar uma exception caso o email que esta sendo cadastrado ja exista")
    void deveLancarExcecaoQuandoEmailJaExiste() throws Exception {
        service.cadastrarUsuario("joe doe", "joe@email.com", TipoUsuario.COMUM);

        assertThrows(EmailAlreadyExistException.class, () -> {
            service.cadastrarUsuario("doe joe", "joe@email.com", TipoUsuario.PREMIUM);
        });

    }

    @Test
    @DisplayName("Deve listar todos os usarios que ja foram cadastrados")
    void deveListarTodosUsuarios() throws Exception {
        service.cadastrarUsuario("joao", "j@email.com", TipoUsuario.COMUM);
        service.cadastrarUsuario("maria", "m@email.com", TipoUsuario.COMUM);
        service.cadastrarUsuario("pedro", "p@email.com", TipoUsuario.PREMIUM);

        assertEquals(3, service.listarUsuarios().size());
    }

}