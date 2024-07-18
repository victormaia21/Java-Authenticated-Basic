# Java (Spring Boot) User Management API

Este projeto é uma API de gerenciamento de usuários construída com Nest.js, usando Prisma como ORM.

## Getting Started local

Para começar com este projeto, siga estes passos:

1. Clone este repositório.
2. Instale as dependências: `npm install`
3. Instale o Nest CLI: `npm install -g @nestjs/cli`
4. Copie o arquivo `.env.example` para `.env` e preencha os valores necessários.
5. Configure a conexão com o banco de dados no arquivo `.env`.
6. Execute as migrações: `npx prisma migrate dev`
7. Inicie o servidor de desenvolvimento: `npm run start:dev`
8. Acesse a documentação Swagger: `http://localhost:3000/api`

## Getting started with Docker

1. Inicie o Docker: `docker-compose up --build`
2. Acesse a documentação Swagger: `http://localhost:3000/api`

## Documentação Swagger

A documentação Swagger também está disponível na URL: [http://localhost:3000/api#/](http://localhost:3000/api)

## .env Example

Certifique-se de criar um arquivo `.env` no diretório raiz do projeto com o seguinte conteúdo:

```dotenv
MYSQL_USER=victor
MYSQL_PASSWORD=9345381276771
MYSQL_DATABASE=authentication_java
MYSQL_LOCAL_PORT=3306
MYSQL_DOCKER_PORT=3306
SPRING_LOCAL_PORT=8080
SPRING_DOCKER_PORT=8080
JWT_SECRET=2391cdismcs921
MYSQL_HOST=mysql
```
