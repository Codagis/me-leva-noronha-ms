# Me Leva Noronha API

API REST desenvolvida em Spring Boot para autenticação e gerenciamento de usuários do aplicativo Me Leva Noronha. O sistema utiliza autenticação JWT (JSON Web Tokens) com suporte a refresh tokens para garantir segurança e experiência do usuário.

## 🚀 Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Spring Boot 3.5.7** - Framework principal
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **JWT (jjwt 0.12.3)** - Tokens de autenticação
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciador de dependências
- **Bean Validation** - Validação de dados

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- Java 17 ou superior
- Maven 3.6+
- PostgreSQL 12+
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code, etc.)

## 🔧 Instalação e Configuração

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd melevanoronha
```

### 2. Configure o Banco de Dados

Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE "me-leva-noronha";
```

### 3. Configure as Variáveis de Ambiente

Edite o arquivo `src/main/resources/application.yml` e ajuste as configurações conforme necessário:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/me-leva-noronha
    username: postgres
    password: postgres
    
jwt:
  secret: me-leva-noronha-secret-key-minimo-256-bits-para-seguranca-jwt-token-apenas-desenvolvimento
  expiration: 28800  # 8 horas em segundos
  refresh-expiration: 604800  # 7 dias em segundos
```

**⚠️ IMPORTANTE:** Para produção, altere a chave JWT secret para uma chave segura e única. Use variáveis de ambiente para armazenar credenciais sensíveis.

### 4. Compile o Projeto

```bash
mvn clean install
```

## ▶️ Como Executar

### Opção 1: Usando Maven Wrapper

**Windows:**
```bash
.\mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

### Opção 2: Usando Maven

```bash
mvn spring-boot:run
```

### Opção 3: Executando o JAR

```bash
mvn clean package
java -jar target/melevanoronha-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Endpoints da API

### Autenticação

Todos os endpoints de autenticação estão disponíveis em `/api/auth/`:

#### 1. Registrar Usuário
```http
POST /api/auth/register
Content-Type: application/json

{
  "nome": "João Silva",
  "username": "joaosilva",
  "email": "joao.silva@example.com",
  "senha": "senha123"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "username": "joaosilva",
  "email": "joao.silva@example.com",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

#### 2. Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "joaosilva",
  "senha": "senha123"
}
```

**Resposta (200 OK):**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "uuid-timestamp",
  "tokenType": "Bearer",
  "expiresIn": 28800
}
```

#### 3. Refresh Token
```http
POST /api/auth/refresh
Content-Type: application/json

{
  "refreshToken": "uuid-timestamp"
}
```

**Resposta (200 OK):**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "new-uuid-timestamp",
  "tokenType": "Bearer",
  "expiresIn": 28800
}
```

#### 4. Logout
```http
POST /api/auth/logout
Content-Type: application/json

{
  "refreshToken": "uuid-timestamp"
}
```

**Resposta (204 No Content):**

### Endpoints Protegidos

Para acessar endpoints protegidos, inclua o token no header:

```http
Authorization: Bearer <accessToken>
```

## 📁 Estrutura do Projeto

```
src/main/java/com/melevanoronha/
├── controller/          # Controladores REST
│   └── AuthenticationController.java
├── dto/                 # Data Transfer Objects
│   ├── AuthResponse.java
│   ├── LoginRequest.java
│   ├── RefreshTokenRequest.java
│   └── RegisterRequest.java
├── exception/           # Tratamento de exceções
│   └── GlobalExceptionHandler.java
├── model/               # Entidades JPA
│   ├── RefreshToken.java
│   └── Usuario.java
├── repository/          # Repositórios Spring Data JPA
│   ├── RefreshTokenRepository.java
│   └── UsuarioRepository.java
├── security/            # Configurações de segurança
│   ├── JwtAuthenticationFilter.java
│   └── SecurityConfiguration.java
└── service/             # Lógica de negócio
    ├── AuthenticationService.java
    ├── JwtService.java
    └── UserDetailsServiceImpl.java
```

## 🔒 Segurança

- **Autenticação JWT**: Tokens de acesso com expiração de 8 horas
- **Refresh Tokens**: Tokens de renovação com expiração de 7 dias
- **BCrypt**: Senhas são criptografadas usando BCrypt
- **Spring Security**: Configuração de segurança com filtros JWT
- **Validação de Dados**: Validação de entrada usando Bean Validation
- **Sessões Stateless**: Aplicação sem estado (stateless)

### Endpoints Públicos

- `/api/auth/**` - Endpoints de autenticação (públicos)
- `/api/public/**` - Endpoints públicos
- `/actuator/**` - Endpoints de monitoramento

### Endpoints Protegidos

Todos os outros endpoints requerem autenticação JWT válida.

## 🧪 Testes

Execute os testes com:

```bash
mvn test
```

## 📦 Collection Postman

Uma collection Postman está disponível no arquivo `Me_Leva_Noronha_API.postman_collection.json`. Importe-a no Postman para testar os endpoints facilmente.

### Configuração no Postman

1. Importe a collection
2. Configure a variável `baseUrl` como `http://localhost:8080`
3. Os tokens serão automaticamente salvos nas variáveis de ambiente após o login

## 🛠️ Desenvolvimento

### Hibernate DDL

O projeto está configurado com `hibernate.ddl-auto=update`, que cria/atualiza automaticamente as tabelas do banco de dados. Para produção, considere usar `validate` ou `none` e usar migrations (Flyway ou Liquibase).

### Logs SQL

O SQL gerado pelo Hibernate é exibido no console. Para desabilitar, altere no `application.yml`:

```yaml
spring:
  jpa:
    show-sql: false
```

## 📝 Validações

### Usuário (RegisterRequest)
- `nome`: obrigatório, máximo 100 caracteres
- `username`: obrigatório, máximo 50 caracteres, único
- `email`: obrigatório, formato válido, máximo 100 caracteres, único
- `senha`: obrigatório, mínimo 6 caracteres

## 🚧 Melhorias Futuras

- [ ] Implementar roles e permissões (RBAC)
- [ ] Adicionar recuperação de senha
- [ ] Implementar confirmação de email
- [ ] Adicionar rate limiting
- [ ] Implementar auditoria de ações
- [ ] Adicionar testes unitários e de integração
- [ ] Configurar CORS adequadamente
- [ ] Implementar logging estruturado
- [ ] Adicionar documentação Swagger/OpenAPI

## 📄 Licença

Este projeto é proprietário e pertence ao Me Leva Noronha.

## 👥 Contribuindo

Este é um projeto interno. Para contribuições, entre em contato com a equipe de desenvolvimento.

## 📞 Suporte

Para questões e suporte, entre em contato com a equipe de desenvolvimento.

---

**Desenvolvido com ❤️ para o Me Leva Noronha**

