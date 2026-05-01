# Executar com Docker Compose

## Prerequisitos
- Docker instalado
- Docker Compose instalado
- Maven (para gerar o .jar)

## Passos para Executar

### 1. Compilar a aplicação
```bash
mvn clean package
```

### 2. Executar os containers
```bash
docker-compose up -d
```

### 3. Acessar a aplicação
- **API:** http://localhost:8080/api/produtos
- **Swagger UI:** http://localhost:8080/swagger-ui/index.html

### 4. Pararar os containers
```bash
docker-compose down
```

### 5. Remover tudo (incluindo volumes)
```bash
docker-compose down -v
```

## Configuração

O `docker-compose.yml` define dois serviços:

### Serviço MySQL
- **Imagem:** mysql:8.0
- **Porta:** 3306
- **Database:** produtos
- **Usuário:** produtos
- **Senha:** produtos123
- **Healthcheck:** Verifica se o MySQL está pronto antes da aplicação iniciar

### Serviço App (api-produtos)
- **Imagem:** Construída a partir do Dockerfile
- **Porta:** 8080
- **Depende de:** mysql (aguarda até estar pronto)
- **Rede:** Comunicação interna via `mysql:3306`

## Variáveis de Ambiente

As seguintes variáveis podem ser customizadas no `docker-compose.yml`:

- `MYSQL_ROOT_PASSWORD` - Senha do root do MySQL
- `MYSQL_DATABASE` - Nome do banco de dados
- `MYSQL_USER` - Usuário do MySQL
- `MYSQL_PASSWORD` - Senha do usuário MySQL
- `SPRING_DATASOURCE_URL` - URL JDBC customizada
- `SPRING_DATASOURCE_USERNAME` - Usuário do Spring
- `SPRING_DATASOURCE_PASSWORD` - Senha do Spring

