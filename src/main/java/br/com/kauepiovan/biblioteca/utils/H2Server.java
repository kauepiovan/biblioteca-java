package br.com.kauepiovan.biblioteca.utils;

import org.h2.tools.Server;

public class H2Server {

    public static void main(String[] args) {
        try {
            // Inicia o servidor web do H2 na porta 8082
            Server server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();

            System.out.println("----------------------------------------------------------");
            System.out.println("H2 Console iniciado com sucesso!");
            System.out.println("Acesse: http://localhost:8082");
            System.out.println("----------------------------------------------------------");
            System.out.println("Dados para conexao:");
            System.out.println("JDBC URL: jdbc:h2:./dados/biblioteca_db;AUTO_SERVER=TRUE");
            System.out.println("User Name: sa");
            System.out.println("Password: (vazio)");
            System.out.println("----------------------------------------------------------");
            System.out.println("Pressione CTRL+C para parar.");

            // Mantem a thread viva
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
