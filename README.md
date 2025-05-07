# Projeto Tasks

Este projeto é composto por duas aplicações integradas:

- **Backend**: Desenvolvido com **Spring Boot**
- **Frontend**: Desenvolvido com **Next.js**

## Requisitos

Antes de iniciar, certifique-se de que você possui os seguintes requisitos instalados:

- Node.js
- NPM
- MySQL
- Java (versão compatível com Spring Boot)

## Configuração Inicial

### 1. Verifique as portas

Certifique-se de que as portas **8080** (para o backend) e **3000** (para o frontend) estão **disponíveis**.

### 2. Configure o banco de dados

Crie o banco de dados MySQL executando o seguinte comando via MySQL Command Line ou MySQL Workbench:

```sql
CREATE DATABASE projetotasks;
```

Em seguida, configure o arquivo `application.properties` do Spring Boot com as suas credenciais do banco, como exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/projetotasks
spring.datasource.username=root
spring.datasource.password=sua_senha
```

### 3. Inicie o backend (Spring Boot)

Garanta que a aplicação backend esteja rodando na porta **8080**. Para isso, basta iniciar o projeto Spring Boot normalmente.

### 4. Configure e inicie o frontend (Next.js)

No diretório do frontend, execute os comandos abaixo:

```bash
npm install
npm run dev
```

O frontend será iniciado na porta **3000**.

> ⚠️ **Importante:** o backend precisa estar ativo para que o frontend funcione corretamente.

## Contato

Caso tenha dúvidas ou problemas, sinta-se à vontade para abrir uma issue ou entrar em contato.
