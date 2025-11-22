# 🚂 Configuração do Railway - Me Leva Noronha API

Este guia explica como configurar e fazer deploy da API Me Leva Noronha no Railway.

## 📋 Pré-requisitos

1. Conta no [Railway](https://railway.app)
2. Repositório Git (GitHub, GitLab ou Bitbucket)
3. Projeto configurado localmente

## 🚀 Passo a Passo para Deploy

### 1. Preparar o Repositório

Certifique-se de que seu código está commitado e enviado para o repositório remoto:

```bash
git add .
git commit -m "Preparar para deploy no Railway"
git push origin main
```

### 2. Criar Projeto no Railway

1. Acesse [railway.app](https://railway.app)
2. Faça login com sua conta GitHub/GitLab
3. Clique em **"New Project"**
4. Selecione **"Deploy from GitHub repo"** (ou GitLab/Bitbucket)
5. Escolha o repositório `me-leva-noronha-ms`

### 3. Adicionar Banco de Dados PostgreSQL

1. No dashboard do projeto, clique em **"+ New"**
2. Selecione **"Database"** → **"Add PostgreSQL"**
3. O Railway criará automaticamente um banco PostgreSQL
4. Anote as credenciais que serão exibidas (ou use as variáveis automáticas)

### 4. Configurar Variáveis de Ambiente

No dashboard do seu serviço, vá em **"Variables"** e adicione as seguintes variáveis:

#### ⚠️ Variáveis Obrigatórias

```bash
# Profile do Spring Boot
SPRING_PROFILES_ACTIVE=prod

# Banco de Dados (Railway fornece automaticamente, mas você pode sobrescrever)
# DATABASE_URL é fornecido automaticamente pelo Railway
# Se necessário, configure manualmente:
# DB_URL=jdbc:postgresql://[host]:[port]/[database]
# DB_USERNAME=[username]
# DB_PASSWORD=[password]

# JWT - ⚠️ CRÍTICO: Gere uma chave secreta forte!
# Gere com: openssl rand -base64 32
JWT_SECRET=sua-chave-secreta-forte-aqui-minimo-32-caracteres

# Flight API (Amadeus)
FLIGHT_API_KEY=sua-chave-amadeus-aqui
FLIGHT_API_SECRET=seu-secret-amadeus-aqui

# Para produção, use a API de produção (não test.api)
FLIGHT_API_BASE_URL=https://api.amadeus.com/v2/shopping/flight-offers
FLIGHT_API_AUTH_URL=https://api.amadeus.com/v1/security/oauth2/token
```

#### 📝 Variáveis Opcionais

```bash
# Hibernate
HIBERNATE_DDL_AUTO=validate
HIBERNATE_SHOW_SQL=false

# Swagger (desabilitado em produção por padrão)
SWAGGER_ENABLED=false

# Weather API (valores padrão já configurados)
WEATHER_API_BASE_URL=https://api.open-meteo.com/v1
WEATHER_LATITUDE=-3.8548
WEATHER_LONGITUDE=-32.4233
WEATHER_TIMEZONE=America/Recife

# Flight API
FLIGHT_API_ENABLED=true
FLIGHT_FALLBACK_PRICE=1500.0

# JWT Expiration (valores padrão)
JWT_EXPIRATION=28800
JWT_REFRESH_EXPIRATION=604800
```

### 5. Configurar Build e Deploy

O Railway detectará automaticamente que é um projeto Maven/Java e usará o arquivo `railway.json` para configuração.

**Configurações importantes:**

- **Build Command**: `mvn clean package -DskipTests`
- **Start Command**: `java -jar target/melevanoronha-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod`
- **Port**: O Railway define automaticamente via variável `PORT`

### 6. Conectar o Banco de Dados ao Serviço

1. No dashboard do seu serviço de aplicação
2. Vá em **"Settings"** → **"Connect Database"**
3. Selecione o banco PostgreSQL criado
4. O Railway configurará automaticamente a variável `DATABASE_URL`

### 7. Fazer Deploy

1. O Railway fará deploy automaticamente após o push
2. Ou clique em **"Deploy"** manualmente
3. Acompanhe os logs em **"Deployments"** → **"View Logs"**

### 8. Verificar o Deploy

Após o deploy, você receberá uma URL pública (ex: `https://me-leva-noronha-production.up.railway.app`)

Teste os endpoints:

```bash
# Health check
curl https://sua-url.railway.app/actuator/health

# Swagger (se habilitado)
https://sua-url.railway.app/swagger-ui.html
```

## 🔧 Configurações Avançadas

### Variáveis de Ambiente do Railway

O Railway fornece automaticamente:

- `PORT` - Porta onde a aplicação deve rodar
- `DATABASE_URL` - URL completa do banco (se conectado)
- `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD` - Credenciais do PostgreSQL

### Custom Domain

1. Vá em **"Settings"** → **"Networking"**
2. Clique em **"Generate Domain"** ou adicione um domínio customizado
3. Configure DNS conforme instruções

### Monitoramento

- **Logs**: Acesse em **"Deployments"** → **"View Logs"**
- **Metrics**: Disponível no dashboard do serviço
- **Health Checks**: Configure em **"Settings"** → **"Health Checks"**

## 🐛 Troubleshooting

### Erro: "Application failed to start"

1. Verifique os logs em **"View Logs"**
2. Confirme que todas as variáveis obrigatórias estão configuradas
3. Verifique se o banco de dados está conectado

### Erro: "Database connection failed"

1. Verifique se o banco está conectado ao serviço
2. Confirme as variáveis `DATABASE_URL` ou `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
3. Verifique se o banco está ativo

### Erro: "Port already in use"

- O Railway define automaticamente a variável `PORT`
- Certifique-se de que `application-prod.properties` usa `${PORT}`

### Build falha

1. Verifique se o `pom.xml` está correto
2. Confirme que o Java 17 está disponível (Railway usa Nixpacks)
3. Verifique os logs de build

## 📝 Checklist de Deploy

- [ ] Código commitado e enviado para o repositório
- [ ] Projeto criado no Railway
- [ ] Banco PostgreSQL adicionado e conectado
- [ ] Variável `SPRING_PROFILES_ACTIVE=prod` configurada
- [ ] Variável `JWT_SECRET` configurada com chave forte
- [ ] Variáveis `FLIGHT_API_KEY` e `FLIGHT_API_SECRET` configuradas
- [ ] `HIBERNATE_DDL_AUTO=validate` configurado
- [ ] `SWAGGER_ENABLED=false` configurado
- [ ] Deploy realizado com sucesso
- [ ] Health check passando
- [ ] Endpoints testados

## 🔒 Segurança em Produção

⚠️ **IMPORTANTE:**

1. **NUNCA** commite credenciais no código
2. Use variáveis de ambiente para todos os secrets
3. Use `HIBERNATE_DDL_AUTO=validate` em produção (nunca `update` ou `create-drop`)
4. Desabilite o Swagger em produção (`SWAGGER_ENABLED=false`)
5. Use chaves JWT fortes e únicas
6. Use a API de produção da Amadeus (não `test.api`)
7. Configure HTTPS (Railway fornece automaticamente)

## 📚 Recursos Adicionais

- [Documentação do Railway](https://docs.railway.app)
- [Railway Discord](https://discord.gg/railway)
- [Spring Boot Profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.profiles)

## 🆘 Suporte

Se encontrar problemas:

1. Verifique os logs no Railway
2. Consulte a documentação do Railway
3. Verifique se todas as variáveis estão configuradas corretamente
4. Teste localmente com `SPRING_PROFILES_ACTIVE=prod`

---

**Última atualização**: Configuração para Railway com profiles dev/prod

