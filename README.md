# Auth API - Authentication API

Projeto de estudos sobre Authorization e JWT Token, criada com **Java, Spring Boot, H2 como banco de dados em memória, Spring Security e JWT**

 
## Descrição

Projeto simples de estudos sobre como implementar Spring Security e JWT Token em uma API.

Possui 2 entidades:

`product` e `user`


## Clone do projeto

Utilize o comando abaixo para clonar o projeto.

Branch `main` contém o projeto inicial antes das implementações.

Branch `add-securyt` contém o projeto final já com as implementações realizadas.

```bash
git clone https://github.com/carloscazelattojr/auth-api-java.git
```


## Executar

http://localhost:8080


## Endpoints

```markdown
GET /product - Listar todos os produtos
GET /product/{id} - Listar um produto pelo id
POST /product - Cadastrar um novo produto (Somente para ADMIN)

POST /auth/login - realizar login na API
POST /auth/register - Cadastrar um novo usuário  (ADMIN, USER) 
```


