# 🚂 Resumo da Configuração Railway

## ✅ Arquivos Criados

### 1. **application-dev.properties** 
📁 `src/main/resources/application-dev.properties`
- Configurações para ambiente de **desenvolvimento**
- Swagger habilitado
- Logs detalhados
- Hibernate em modo `update`

### 2. **application-prod.properties**
📁 `src/main/resources/application-prod.properties`
- Configurações para ambiente de **produção**
- Swagger desabilitado por padrão
- Hibernate em modo `validate` (seguro)
- Pool de conexões otimizado
- Logs otimizados

### 3. **railway.json**
📁 `railway.json`
- Configuração do build e deploy no Railway
- Comando de build: `mvn clean package -DskipTests`
- Comando de start: `java -jar target/melevanoronha-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod`

### 4. **application.yml** (Atualizado)
📁 `src/main/resources/application.yml`
- Agora suporta profiles via `SPRING_PROFILES_ACTIVE`
- Suporta variável `PORT` do Railway

## 🔧 Como Usar

### Desenvolvimento Local

```bash
# Ativar profile dev
export SPRING_PROFILES_ACTIVE=dev
# ou
java -jar app.jar --spring.profiles.active=dev
```

### Produção (Railway)

Configure no Railway:
```bash
SPRING_PROFILES_ACTIVE=prod
```

## 📝 Variáveis de Ambiente no Railway

### ⚠️ OBRIGATÓRIAS:

```bash
SPRING_PROFILES_ACTIVE=prod
JWT_SECRET=sua-chave-secreta-forte-aqui
FLIGHT_API_KEY=sua-chave-amadeus
FLIGHT_API_SECRET=seu-secret-amadeus
```

### 📋 OPCIONAIS (com valores padrão):

```bash
HIBERNATE_DDL_AUTO=validate
SWAGGER_ENABLED=false
PORT=8080  # Railway define automaticamente
```

## 🚀 Próximos Passos

1. **Commit e Push:**
   ```bash
   git add .
   git commit -m "Configuração para Railway com profiles dev/prod"
   git push origin main
   ```

2. **No Railway:**
   - Criar novo projeto
   - Conectar repositório
   - Adicionar PostgreSQL
   - Configurar variáveis de ambiente
   - Fazer deploy

3. **Ver documentação completa:**
   - Leia `RAILWAY_SETUP.md` para instruções detalhadas

## 📚 Arquivos de Referência

- `RAILWAY_SETUP.md` - Guia completo de configuração
- `env.example` - Exemplo de variáveis de ambiente
- `application-dev.properties` - Configurações de dev
- `application-prod.properties` - Configurações de produção

---

**Tudo pronto para deploy no Railway! 🎉**

