
# DocDoctor - gerenciador de arquivos de consultório medico

## Descrição do Projeto

Este projeto é um microsserviço que tem como objetivo gerenciar usuários que podem acessar os seus arquivos enviados pelo médico em uma eventual consulta(ex.: atestado, pedido médico entre outros arquivos).

## Funcionalidades - endpoints

- **Consulta de usuário por id:** GET /api/users/{id};
- **Atualiza informacoes do usuário**: PUT /api/users/{id};
- **Deleta usuário**: DELETE /api/users/{id};
- **Lista todos os usuários:** GET /api/users;
- **Lista usuários com paginação:** GET /api/users/paged;
- **Cria usuários**: POST /api/users.

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
```

## Passos para Executar o Projeto

### Pré-requisitos 

- **Java 17**
- **Maven 3.10.1**

### Executar a Aplicação

1. Clone o repositório:

    ```bash
    git clone https://github.com/andresavasconcelos/docdoctor.git
    cd docdoctor
    ```

2. Compile e execute o projeto:

    ```bash
    mvn clean install
    ```

3. Acesse o serviço:

   O serviço estará disponível em `http://localhost:8080`.
   O swagger estará disponível em `http://localhost:8080/swagger-ui/index.html`.


### Executar Testes

Para executar os testes unitários:

```bash
mvn clean test
```