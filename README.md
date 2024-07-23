# Java (Spring Boot) User Management API

Este projeto é uma API de gerenciamento de usuários construída com Java (Spring Boot).

## Getting Started local

Para começar com este projeto, siga estes passos:

1. Clone este repositório.
2. Abra o mysql e crie um banco de dados com um nome de sua preferencia
3. Copie o arquivo `.env.example` para `.env` e preencha os valores necessários.
4. Configure a conexão com o banco de dados no arquivo `.env`.
5. Modifique o arquivo `application.yml` na pasta `resources` com as configurações de banco de dados correta
6. Inicie o servidor de desenvolvimento: `mvn spring-boot:run`
7. Acesse a documentação Swagger: `http://localhost:8080/swagger-ui/index.html`

## Getting started with Docker

1. Inicie o Docker: `docker-compose up --build`
2. Acesse a documentação Swagger: `http://localhost:8080/swagger-ui/index.html`

## Documentação Swagger

A documentação Swagger também está disponível na URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## .env Example

Certifique-se de criar um arquivo `.env` no diretório raiz do projeto com o seguinte conteúdo:

```dotenv
MYSQL_USER=DATABASE_USERNAME
MYSQL_PASSWORD=PASSWORD
MYSQL_DATABASE=DATABASE_NAME
MYSQL_LOCAL_PORT=3306
MYSQL_DOCKER_PORT=3306
SPRING_LOCAL_PORT=8080
SPRING_DOCKER_PORT=8080
JWT_SECRET=JWT_SECRET
MYSQL_HOST=mysql
```
