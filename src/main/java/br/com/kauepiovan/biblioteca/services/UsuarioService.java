package br.com.kauepiovan.biblioteca.services;

import br.com.kauepiovan.biblioteca.domain.enums.TipoUsuario;
import br.com.kauepiovan.biblioteca.domain.model.Usuario;
import br.com.kauepiovan.biblioteca.repository.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    private boolean emailExists(String email) {
        return !repository.getOne(email).isEmpty();
    }

    public void cadastrarUsuario(String nome, String email, TipoUsuario tipo) {
        if (emailExists(email)) {
            throw new IllegalStateException("Email informa ja existe cadastro: " + email);
        }
        var usuario = new Usuario(nome, email, tipo);
        repository.addOne(usuario);
    }

    public void listarUsuarios() {
        var usuarios = repository.getAll();
        System.out.println("\n=== Lista de Usuários ===");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (var usuario : usuarios) {
            System.out.println("- Nome: " + usuario.getNome() + " | Email: " + usuario.getEmail() + " | Tipo: "
                    + usuario.getTipo());
            var emprestados = usuario.getLivrosEmprestados();
            if (emprestados == null || emprestados.isEmpty()) {
                System.out.println("  Livros emprestados: nenhum");
            } else {
                System.out.println("  Livros emprestados:");
                for (var livro : emprestados) {
                    System.out.println("    - " + livro.getTitulo() + " | Autor: " + livro.getAutor() + " | Status: "
                            + livro.getStatus());
                }
            }
        }
    }

}
