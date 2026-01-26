# Biblioteca - Sistema de Gerenciamento

Sistema de gerenciamento de biblioteca desenvolvido em Java, focado na aplicação de boas práticas de arquitetura e conceitos fundamentais da linguagem.

## 1. Visão Geral
Este projeto é uma aplicação **CLI (Command Line Interface)** para administração de exemplares, usuários e empréstimos de uma biblioteca. O sistema permite realizar operações de cadastro, listagem e controle de fluxo de empréstimos, simulando um ambiente real de negócios.

## 2. Objetivo
O objetivo principal é o estudo e prática de desenvolvimento de software em Java, explorando:
- **Arquitetura em Camadas (Layered Architecture)** para separação de responsabilidades.
- **Injeção de Dependências** manual.
- **Conceitos de POO** (Polimorfismo, Abstração, Encapsulamento).
- **Java 21** e suas features modernas (Records, Streams, Optional).
- **Testes Unitários** com JUnit e Mockito.
- **Persistência de Dados** com JPA e Hibernate.

## 3. Tecnologias e Ferramentas
- **Linguagem**: Java 21
- **Gerenciamento de Dependências**: Maven
- **Testes**: JUnit 5, Mockito Core 5.21
- **Persistência**: H2 Database (Arquivo Local)
- **ORM**: Hibernate Core 6.4.1 (JPA)

## 4. Arquitetura e Organização
O projeto segue uma arquitetura em camadas bem definida, garantindo desacoplamento e facilidade de manutenção.

### Estrutura de Pacotes (`br.com.kauepiovan.biblioteca`)
- **`application`**: Contém o ponto de entrada (`App.java`) e a lógica de UI (`Menu`).
- **`controller`**: Responsável por processar a entrada do usuário e delegar para os serviços.
- **`services`**: Contém as regras de negócio e validações do sistema.
- **`repository`**: Camada de acesso a dados.
    - Implementação genérica com `BaseRepository` utilizando `EntityManager`.
    - Persistência real via JDBC/H2.
- **`domain`**: Entidades core (`Livro`, `Usuario`, `Emprestimo`) mapeadas com anotações JPA.
- **`exceptions`**: Exceções personalizadas para tratamento de erros de domínio.
- **`utils`**: Utilitários do sistema (ex: `H2Server` para o console do banco).

## 5. Como Executar o Projeto

### Pré-requisitos
- Java JDK 21 instalado.
- Maven instalado e configurado.

### Passos
1. **Clone o repositório** (ou baixe os arquivos).
2. **Compile o projeto**:
   ```bash
   mvn clean install
   ```
3. **Execute a aplicação**:
   Você pode executar diretamente pela sua IDE (IntelliJ/Eclipse/VS Code) rodando a classe `App.java`.

### Acessando o Banco de Dados (H2 Console)
Para visualizar os dados e executar queries SQL diretamente:
1. Execute a classe `br.com.kauepiovan.biblioteca.utils.H2Server` via IDE.
2. Acesse no navegador: `http://localhost:8082`
3. Utilize a seguinte conexão:
   - **JDBC URL**: `jdbc:h2:./dados/biblioteca_db;AUTO_SERVER=TRUE`
   - **User Name**: `sa`
   - **Password**: (vazio)

**Importante**: A configuração `AUTO_SERVER=TRUE` permite que a aplicação e o console acessem o banco simultaneamente.

## 6. Testes
O projeto possui uma estrutura de testes unitários localizada em `src/test/java`, utilizando **JUnit 5**, **Mockito** e um banco H2 em memória (`jdbc:h2:mem:testdb`) para testes de integração da camada de persistência.

## 7. Limitações e Observações Técnicas
- **Interface**: Interação via terminal (Console), sem interface gráfica ou REST API.
- **Dados Iniciais**: A aplicação pode iniciar com um conjunto de dados para testes se configurado no `App.java`.
- **Banco de Dados**: O arquivo do banco é gerado na pasta `./dados`.

## 8. Licença
Este projeto não possui uma licença especificada.
