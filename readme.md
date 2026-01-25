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

## 3. Tecnologias e Ferramentas
- **Linguagem**: Java 21
- **Gerenciamento de Dependências**: Maven
- **Testes**: JUnit 5, Mockito Core 5.21
- **Persistência**: In-Memory (estruturas de dados em memória)

## 4. Arquitetura e Organização
O projeto segue uma arquitetura em camadas bem definida, garantindo desacoplamento e facilidade de manutenção.

### Estrutura de Pacotes (`br.com.kauepiovan.biblioteca`)
- **`application`**: Contém o ponto de entrada (`App.java`) e a lógica de UI (`Menu`).
- **`controller`**: Responsável por processar a entrada do usuário e delegar para os serviços.
- **`services`**: Contém as regras de negócio e validações do sistema.
- **`repository`**: Camada de acesso a dados.
    - Implementação baseada em memória (`InMemoryRepository`).
    - Abstração via interfaces.
- **`domain`**: Entidades core (`Livro`, `Usuario`, `Emprestimo`) e Enums.
- **`exceptions`**: Exceções personalizadas para tratamento de erros de domínio.

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

## 6. Testes
O projeto possui uma estrutura de testes unitários localizada em `src/test/java`, utilizando **JUnit 5** e **Mockito** para garantir a qualidade das regras de negócio e isolar o comportamento das camadas.

## 7. Limitações e Observações Técnicas
- **Persistência Volátil**: O sistema utiliza armazenamento em memória (`ArrayList`). Todos os dados são perdidos ao reiniciar a aplicação.
- **Interface**: Interação via terminal (Console), sem interface gráfica ou REST API.
- **Dados Iniciais**: A aplicação inicia com um conjunto de dados "mockados" para facilitar os testes manuais.

## 8. Licença
Este projeto não possui uma licença especificada.
