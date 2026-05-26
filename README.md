# 📋 Cobrança API

API REST para gestão de cobrança empresarial, desenvolvida em Kotlin com Spring Boot 4. Implementa arquitetura em camadas com particionamento por domínio de negócio, cobrindo o ciclo completo de clientes, títulos a receber e registro de interações de atendimento. Utiliza PostgreSQL como banco de dados relacional, Flyway para versionamento de schema e SpringDoc/Swagger para documentação interativa dos endpoints.

---

## 🚀 Tecnologias

| Tecnologia | Versão |
|---|---|
| Kotlin | 2.2.21 |
| Spring Boot | 4.0.7 |
| Java | 21 |
| PostgreSQL | — |
| Flyway | — |
| SpringDoc OpenAPI (Swagger) | 3.0.2 |

---

## 📋 Pré-requisitos

- JDK 21+
- PostgreSQL rodando localmente
- Gradle (ou usar o wrapper `./gradlew` incluso)

---

## ⚙️ Configuração

### Banco de dados

Crie o banco de dados no PostgreSQL:

```sql
CREATE DATABASE cobranca;
```

### Variáveis de ambiente

Copie o arquivo de exemplo e ajuste com suas credenciais locais:

```bash
cp .env.example .env
```

As variáveis esperadas são:

```properties
DB_URL=jdbc:postgresql://localhost:5432/cobranca
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

O `application.properties` lê essas variáveis automaticamente. As migrations são executadas pelo Flyway na inicialização da aplicação.

### Perfil local (alternativa sem variáveis de ambiente)

Crie o arquivo `src/main/resources/application-local.properties` com os valores reais e ative o perfil ao executar:

```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```

> Este arquivo está no `.gitignore` e não deve ser versionado.

---

## ▶️ Como executar

```bash
# Clone o repositório
git clone https://github.com/Lucasscheere/cobranca.git
cd cobranca

# Execute a aplicação
./gradlew bootRun
```

A aplicação estará disponível em `http://localhost:8080`.

A documentação interativa (Swagger UI) estará em `http://localhost:8080/swagger-ui.html`.

---

## 🗂️ Estrutura do projeto

```
src/main/kotlin/br/net/cobranca/
├── account/
│   ├── dto/
│   │   ├── AccountRequestDTO.kt
│   │   └── AccountResponseDTO.kt
│   ├── Account.kt
│   ├── AccountController.kt
│   ├── AccountRepository.kt
│   ├── AccountService.kt
│   ├── PaymentMethod.kt
│   └── StatusPayment.kt
├── client/
│   ├── dto/
│   │   ├── ClientRequestDTO.kt
│   │   └── ClientResponseDTO.kt
│   ├── Client.kt
│   ├── ClientController.kt
│   ├── ClientRepository.kt
│   └── ClientService.kt
├── interaction/
│   ├── dto/
│   │   ├── InteractionRequestDTO.kt
│   │   └── InteractionResponseDTO.kt
│   ├── Interaction.kt
│   ├── InteractionController.kt
│   ├── InteractionRepository.kt
│   └── InteractionService.kt
└── CobrancaApplication.kt

src/main/resources/
├── db/migration/
│   ├── V1__create_clients.sql
│   ├── V2__create_accounts.sql
│   └── V3__create_interactions.sql
└── application.properties
```

---

## 📡 Endpoints

### Clientes — `/clients`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/clients` | Lista todos os clientes |
| GET | `/clients/{id}` | Busca cliente por ID |
| POST | `/clients` | Cadastra novo cliente |
| DELETE | `/clients/{id}` | Remove cliente |

**Exemplo de requisição (POST):**
```json
{
  "cnpj": "12.345.678/0001-99",
  "razaoSocial": "Empresa Exemplo LTDA",
  "nomeFantasia": "Exemplo",
  "active": true
}
```

**Exemplo de resposta:**
```json
{
  "id": 1,
  "cnpj": "12.345.678/0001-99",
  "razaoSocial": "Empresa Exemplo LTDA",
  "nomeFantasia": "Exemplo",
  "active": true,
  "createdAt": "2026-05-26"
}
```

---

### Contas — `/account`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/account` | Lista todas as contas |
| GET | `/account/{id}` | Busca conta por ID |
| POST | `/account` | Cadastra nova conta |
| DELETE | `/account/{id}` | Remove conta |

**Métodos de pagamento disponíveis:** `DINHEIRO`, `BOLETO`, `PIX`, `CARTAO`, `VALE`

**Status de pagamento (calculado automaticamente pelo servidor):**

| Status | Condição |
|---|---|
| `VINCENDO` | Data de vencimento ainda não atingida |
| `ATRASADO` | Data de vencimento ultrapassada sem pagamento |
| `PAGO` | Data de pagamento informada na criação |

**Exemplo de requisição (POST):**
```json
{
  "clientId": 1,
  "paymentMethod": "PIX",
  "issueDate": "2026-05-26",
  "dueDate": "2026-06-26",
  "valuePayment": 1500.00,
  "notes": "Parcela referente ao mês de junho"
}
```

**Exemplo de resposta:**
```json
{
  "id": 1,
  "clientId": 1,
  "paymentMethod": "PIX",
  "issueDate": "2026-05-26",
  "paymentDate": null,
  "dueDate": "2026-06-26",
  "valuePayment": 1500.00,
  "statusPayment": "VINCENDO",
  "notes": "Parcela referente ao mês de junho"
}
```

---

### Interações — `/interactions`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/interactions` | Lista todas as interações |
| GET | `/interactions/{id}` | Busca interação por ID |
| POST | `/interactions` | Registra nova interação |
| DELETE | `/interactions/{id}` | Remove interação |

**Exemplo de requisição (POST):**
```json
{
  "clientId": 1,
  "notes": "Cliente solicitou prazo adicional para pagamento.",
  "nextContact": "2026-06-01T10:00:00"
}
```

---

## 🗃️ Modelo de dados

```
client
├── id (PK)
├── cnpj (unique)
├── razao_social
├── nome_fantasia
├── active
└── created_at

account
├── id (PK)
├── client_id (FK → client)
├── payment_method
├── issue_date
├── payment_date
├── due_date
├── value_payment
├── status_payment
└── notes

interaction
├── id (PK)
├── client_id (FK → client)
├── notes
├── next_contact
└── created_at
```

---

## 🔜 Próximas melhorias

- [ ] Tratamento global de exceções (`GlobalExceptionHandler`)
- [x] Validações nos DTOs de entrada (`@NotNull`, `@Positive`, `@Future`, `@CNPJ`)
- [x] DTOs de request e response para todos os módulos (`Client`, `Account`, `Interaction`)
- [x] Status de pagamento calculado automaticamente pelo servidor (sem entrada do cliente)
- [x] Credenciais externalizadas via variáveis de ambiente
- [ ] Endpoints de atualização (`PUT`/`PATCH`) para contas e interações
- [ ] Paginação nos endpoints de listagem
- [ ] Autenticação e autorização com Spring Security + JWT
- [ ] Testes unitários e de integração
- [ ] Containerização com Docker e Docker Compose

---

## 👤 Autor

**Lucas Scheere Rodrigues da Silva**
Analista de Crédito | Desenvolvedor em formação
[LinkedIn](https://www.linkedin.com/in/lucas-scheere/) · [GitHub](https://github.com/Lucasscheere)