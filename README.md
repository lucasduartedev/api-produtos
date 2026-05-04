# API Rest de Produtos - Java & Spring Boot 🚀

## 📋 Visão Geral

API REST desenvolvida com **Java e Spring Boot** para gerenciamento completo de produtos em banco de dados **MySQL**. A aplicação implementa operações CRUD (Create, Read, Update, Delete) com exclusão lógica de registros, suporte a paginação e documentação automática via Swagger/OpenAPI.

## ✨ Características Principais

- ✅ **CRUD Completo** - Criar, ler, atualizar e deletar produtos
- ✅ **Exclusão Lógica** - Produtos não são permanentemente deletados, apenas marcados como inativos
- ✅ **Reativação de Produtos** - Permite reativar produtos deletados logicamente
- ✅ **Paginação** - Listagem de produtos com suporte a paginação e ordenação
- ✅ **Validação de Dados** - Validações robustas com Jakarta Validation
- ✅ **Documentação Automática** - Swagger UI/OpenAPI integrado para fácil visualização da API
- ✅ **Spring Security** - Necessário para liberar acesso ao Swagger, pois a aplicação bloqueia por padrão
- ✅ **Cadastro em Lote** - Endpoint para cadastrar múltiplos produtos de uma vez
- ✅ **Containerização** - Docker Compose para realizar o deployment da aplicação
- ✅ **Migrations de BD** - Versionamento de schema com Flyway

## Próximas implementações

- ✅ **Spring Test** - Implementação de teste unitários e de integração
- ✅ **Spring Security** - Implementar mais segurança a aplicação
- ✅ **JWT** - Autenticação e autorização para controlar o uso da aplicação

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 17 | Linguagem de programação |
| **Spring Boot** | 3.2.4 | Framework web e aplicação |
| **Spring Data JPA** | 3.2.4 | Persistência de dados |
| **Spring Security** | 3.2.4 | Segurança da aplicação |
| **MySQL** | 8.0 | Banco de dados relacional |
| **Flyway** | 9.22.3 | Migrations de banco de dados |
| **SpringDoc OpenAPI** | 2.3.0 | Documentação Swagger/OpenAPI |
| **Jakarta Validation** | 3.0.2 | Validação de dados |
| **Lombok** | 1.18.30 | Redução de código boilerplate |
| **Maven** | 3.9+ | Gerenciador de dependências |
| **Docker** | Latest | Containerização |

## 📦 Requisitos

- Java 17 ou superior
- Maven 3.9 ou superior
- Docker e Docker Compose (para executar com containers)
- MySQL 8.0 (ou usar Docker Compose)

## 🚀 Como Executar

### 1️⃣ Executar Localmente com Maven (apenas a aplicação)

```bash
# Compilar o projeto
mvn clean package

# Executar a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

Obs: O banco de dados deve está disponível antes de rodar a aplicação. 

### 2️⃣ Executar com Docker Compose (Recomendado)

```bash
# Compilar o projeto
mvn clean package

# Iniciar os containers
docker-compose up -d

# Parar os containers
docker-compose down
```

A aplicação estará disponível em: `http://localhost:8080`
O MySQL estará acessível em: `localhost:3306` (dentro dos containers)

## 📚 Documentação da API

### Swagger UI
Acesse a documentação interativa em:
```
http://localhost:8080/swagger-ui/index.html
```

### OpenAPI JSON
Obtenha o arquivo de documentação OpenAPI em:
```
http://localhost:8080/v3/api-docs
```

## 📊 Endpoints da API

### Produtos

| Método | Endpoint                    | Descrição |
|--------|-----------------------------|-----------|
| `POST` | `/api/produtos`             | Cadastrar um novo produto |
| `POST` | `/api/produtos/lote`        | Cadastrar múltiplos produtos |
| `GET` | `/api/produtos`             | Listar produtos ativos com paginação |
| `GET` | `/api/produtos/{id}`        | Buscar produto por ID |
| `PUT` | `/api/produtos/{id}`        | Atualizar um produto |
| `DELETE` | `/api/produtos/{id}`        | Deletar (exclusão lógica) um produto |
| `GET` | `/api/produtos/ativar/{id}` | Reativar um produto deletado |

## 📝 Exemplos de Uso

### 1. Cadastrar um Produto

```bash
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Notebook",
    "descricao": "Notebook de alta performance",
    "preco": 3500.00
  }'
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "nome": "Notebook",
  "descricao": "Notebook de alta performance",
  "preco": 3500.00,
  "ativo": true
}
```

### 2. Cadastrar Múltiplos Produtos

```bash
curl -X POST http://localhost:8080/api/produtos/lista \
  -H "Content-Type: application/json" \
  -d '[
    {
      "nome": "Mouse",
      "descricao": "Mouse wireless",
      "preco": 89.90
    },
    {
      "nome": "Teclado",
      "descricao": "Teclado mecânico",
      "preco": 450.00
    }
  ]'
```

### 3. Listar Produtos com Paginação

```bash
curl -X GET "http://localhost:8080/api/produtos"
```

### 4. Buscar Produto por ID

```bash
curl -X GET http://localhost:8080/api/produtos/1
```

### 5. Atualizar um Produto

```bash
curl -X PUT http://localhost:8080/api/produtos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Notebook Atualizado",
    "descricao": "Nova descrição",
    "preco": 3800.00
  }'
```

### 6. Deletar um Produto

```bash
curl -X DELETE http://localhost:8080/api/produtos/1
```

### 7. Reativar um Produto

```bash
curl -X GET http://localhost:8080/api/produtos/ativar/1
```

## 📁 Estrutura do Projeto

```
api-produtos/
├── src/main/java/com/api/produtos/
│   ├── controller/
│   │   └── ProdutoController.java      # Endpoints da API
│   ├── service/
│   │   └── ProdutoService.java         # Lógica de negócio
│   ├── models/
│   │   └── Produto.java                # Entidade JPA
│   ├── repository/
│   │   └── ProdutoRepository.java      # Acesso aos dados
│   ├── dto/
│   │   ├── ProdutoCadastrarDTO.java    # DTO para cadastro
│   │   ├── ProdutoAtualizarDTO.java    # DTO para atualização
│   │   └── ProdutoListagemDTO.java     # DTO para listagem
│   ├── config/
│   │   ├── SecurityConfig.java         # Configuração de segurança
│   │   └── SwaggerConfig.java          # Configuração do Swagger
│   └── ApiProdutosApplication.java     # Classe principal
├── src/main/resources/
│   ├── application.yaml                # Configurações da aplicação
│   ├── application-docker.yaml         # Configurações para Docker
│   └── db/migration/
│       └── V1__create-table-produtos.sql # Schema inicial
├── docker-compose.yml                  # Orquestração de containers
├── Dockerfile                          # Imagem Docker da aplicação
├── pom.xml                             # Dependências Maven
└── README.md                           # Este arquivo
```
<!--
## 🔒 Segurança

A aplicação utiliza **Spring Security** para proteger os endpoints. O Swagger UI está configurado para acesso público, permitindo a visualização da documentação da API sem autenticação.
-->

## 💾 Banco de Dados

### Estrutura da Tabela Produtos (será alterada)

```sql
CREATE TABLE produtos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  descricao TEXT,
  preco DECIMAL(10, 2) NOT NULL,
  ativo BOOLEAN DEFAULT true
);
```

<!--
  criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
-->

As migrations são gerenciadas automaticamente pelo **Flyway**.

## 👨‍💻 Autor

Lucas Santana Duarte

<!--
## 🧪 Testes

Para executar os testes:

```bash
mvn test
```

## 📄 Licença

Este projeto é fornecido como exemplo educacional.

## 👨‍💻 Autor

Desenvolvido como prática de **API REST com Spring Boot**

-->

---

**Última atualização:** 04/05/2026
