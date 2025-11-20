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

**⚠️ IMPORTANTE:** Para segurança, todas as credenciais sensíveis devem ser configuradas via variáveis de ambiente. 

#### Opção 1: Usando arquivo .env (Recomendado para desenvolvimento local)

1. Copie o arquivo `env.example` para `.env`:
   ```bash
   cp env.example .env
   ```

2. Edite o arquivo `.env` e configure as variáveis conforme seu ambiente:
   ```bash
   # Banco de Dados
   DB_URL=jdbc:postgresql://localhost:5432/me-leva-noronha
   DB_USERNAME=postgres
   DB_PASSWORD=postgres
   
   # JWT - ⚠️ Use uma chave secreta forte em produção!
   JWT_SECRET=sua-chave-secreta-aqui-minimo-256-bits
   
   # Flight API (Amadeus)
   FLIGHT_API_KEY=sua-chave-aqui
   FLIGHT_API_SECRET=seu-secret-aqui
   ```

3. Para usar o arquivo `.env` com Spring Boot, você pode usar ferramentas como:
   - **IntelliJ IDEA**: Configura automaticamente variáveis de ambiente do `.env`
   - **VS Code**: Use a extensão "DotENV" ou configure manualmente
   - **Linha de comando**: Use `export $(cat .env | xargs)` antes de executar a aplicação

#### Opção 2: Definir variáveis de ambiente diretamente

**Windows (PowerShell):**
```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/me-leva-noronha"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"
$env:JWT_SECRET="sua-chave-secreta-aqui"
```

**Linux/Mac:**
```bash
export DB_URL="jdbc:postgresql://localhost:5432/me-leva-noronha"
export DB_USERNAME="postgres"
export DB_PASSWORD="postgres"
export JWT_SECRET="sua-chave-secreta-aqui"
```

#### Variáveis de Ambiente Disponíveis

**⚠️ OBRIGATÓRIAS (sem valores padrão no código):**

| Variável | Descrição | Obrigatória |
|----------|-----------|-------------|
| `DB_URL` | URL de conexão do PostgreSQL | ✅ Sim |
| `DB_USERNAME` | Usuário do banco de dados | ✅ Sim |
| `DB_PASSWORD` | Senha do banco de dados | ✅ Sim |
| `JWT_SECRET` | **Chave secreta para JWT** | ✅ Sim |
| `FLIGHT_API_KEY` | Chave da API Amadeus | ✅ Sim |
| `FLIGHT_API_SECRET` | Secret da API Amadeus | ✅ Sim |

**Opcionais (com valores padrão):**

| Variável | Descrição | Padrão |
|----------|-----------|--------|
| `JWT_EXPIRATION` | Tempo de expiração do token JWT (segundos) | `28800` (8 horas) |
| `JWT_REFRESH_EXPIRATION` | Tempo de expiração do refresh token (segundos) | `604800` (7 dias) |
| `SERVER_PORT` | Porta do servidor | `8080` |
| `HIBERNATE_DDL_AUTO` | Modo do Hibernate (update/validate/none) | `update` |
| `HIBERNATE_SHOW_SQL` | Exibir SQL no console | `true` |
| `SWAGGER_ENABLED` | Habilitar Swagger UI | `true` |
| `FLIGHT_FALLBACK_PRICE` | Preço padrão quando API falha | `1500.0` |

**⚠️ SEGURANÇA:**
- **NUNCA** commite o arquivo `.env` no repositório
- Em **produção**, use variáveis de ambiente do sistema ou ferramentas de gerenciamento de secrets (AWS Secrets Manager, HashiCorp Vault, etc.)
- Gere uma chave JWT forte em produção: `openssl rand -base64 32`
- Desabilite o Swagger em produção: `SWAGGER_ENABLED=false`
- Use `HIBERNATE_DDL_AUTO=validate` ou `none` em produção

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

