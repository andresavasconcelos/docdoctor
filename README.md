
# DocDoctor - gerenciador de arquivos de consultório medico

## Descrição do Projeto

Este projeto é um microsserviço que tem como objetivo gerenciar usuários que podem acessar os seus arquivos enviados pelo médico em uma eventual consulta(ex.: atestado, pedido médico entre outros arquivos).

## Funcionalidades - endpoints

- **Consulta de usuário por ID:** GET /api/users/{id};
- **Atualiza informações do usuário**: PUT /api/users/{id};
- **Remove usuário**: DELETE /api/users/{id};
- **Lista todos os usuários:** GET /api/users;
- **Lista usuários com paginação:** GET /api/users/paged;
- **Cadastrar novo usuários**: POST /api/users.

## Estrutura do Projeto

```bash
docdoctor
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br.com.docdoctor
│   │   │       ├── DocdoctorApplication.java
│   │   │       ├── config
│   │   │       │   └── OpenApiConfig.java
│   │   │       ├── controller
│   │   │       │   └── UserController.java
│   │   │       ├── dto
│   │   │       │   └── ErrorDTO.java
│   │   │       │   └── FieldErrorDTO.java
│   │   │       │   └── UserPaginationDTO.java
│   │   │       │   └── UserRequestDTO.java
│   │   │       │   └── UserResponseDTO.java
│   │   │       ├── entities
│   │   │       │   └── User.java
│   │   │       ├── enums
│   │   │       │   └── UserTypeEnum.java
│   │   │       ├── exception
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       │   └── UserNotFoundException.java
│   │   │       ├── mapper
│   │   │       │   └── UserMapper.java
│   │   │       ├── repository
│   │   │       │   └── UserRepository.java
│   │   │       ├── service
│   │   │       │   └── user
│   │   │       │        └── IUserService.java
│   │   │       │        └── UserServiceImpl.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       ├── java
│       │   └── br.com.docdoctor
│       │       └── controller/user
│       │       │   └── UserControllerIntegrationTest.java
│       │       └── service/user
│       │           └── UserServiceImplTest.java
│       └── resources
└── pom.xml
└── scripts 
      └── model_docdoctor.mwb
      └── script_create_docdoctor_db.sql
      └── script_create_users.sql
  
```

## Passos para Executar o Projeto

### Pré-requisitos 

- **Java 17**
- **Maven 3.10.1+**
- **Docker instalado**
- **MySQL ou outro SGBD compatível**

### Executar a Aplicação

1. Clone o repositório:

    ```bash
    git clone https://github.com/andresavasconcelos/docdoctor.git
    cd docdoctor
    ```

2. Execute o banco de dados com docker

    ```bash
    docker compose up
    ```
3. Compile e execute o projeto:

    ```bash
    mvn clean install
    ```

3. Acesse o serviço:

  - Serviço: `http://localhost:8080`.
  - Documentação (Swagger UI): `http://localhost:8080/swagger-ui/index.html`.


### Executar Testes

Para executar os testes unitários:

```bash
mvn clean test
```