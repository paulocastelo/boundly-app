
# 📱 Boundly App API

API REST para gerenciar contatos, grupos e encontros entre pessoas, permitindo organização social eficiente de relacionamentos.

---

## 🚀 Como Rodar o Projeto

### ✔️ Pré-requisitos

- **Java 21**
- **Maven 3.9+**
- IDE recomendada: IntelliJ IDEA ou Eclipse

### 🔧 Executando Localmente

```bash
git clone https://github.com/SEU_USUARIO/boundly-app-api.git
cd boundly-app-api
mvn clean install
mvn spring-boot:run
````

A aplicação estará disponível em:
👉 [http://localhost:8080](http://localhost:8080)
👉 Documentação Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📦 Dependências Principais

* Spring Boot 3.2.5
* Spring Web
* Spring Data JPA
* Hibernate Validator
* Banco H2 (Dev) / PostgreSQL (Produção)
* OpenAPI (springdoc-openapi 2.3.0)

---

## 🔗 Endpoints Disponíveis

### 📍 Pessoas (`/pessoas`)

| Método | Rota            | Ação                       |
| ------ | --------------- | -------------------------- |
| GET    | `/pessoas`      | Lista todas as pessoas     |
| GET    | `/pessoas/{id}` | Retorna uma pessoa por ID  |
| POST   | `/pessoas`      | Cria uma nova pessoa       |
| PUT    | `/pessoas/{id}` | Atualiza uma pessoa por ID |
| PATCH  | `/pessoas/{id}` | Atualiza parcialmente      |
| DELETE | `/pessoas/{id}` | Remove uma pessoa          |

> 🧠 *Outros recursos (Assunto, Agendamento, etc.) seguem a mesma lógica.*

---

## 🧪 Exemplos de Requisições JSON

### 📤 Criar Pessoa

```json
POST /pessoas
Content-Type: application/json

{
  "nome": "João da Silva",
  "email": "joao.silva@email.com",
  "telefone": "11999998888"
}
```

### ✏️ Atualizar Pessoa (PUT)

```json
PUT /pessoas/1
Content-Type: application/json

{
  "nome": "João Atualizado",
  "email": "joao.novo@email.com",
  "telefone": "11999997777"
}
```

### 🔧 Atualização Parcial (PATCH)

```json
PATCH /pessoas/1
Content-Type: application/json

{
  "email": "joao.patch@email.com"
}
```

---

## 🧼 Retorno de Erros de Validação

```json
{
  "status": 400,
  "errors": [
    "O campo nome é obrigatório",
    "Formato de e-mail inválido"
  ],
  "path": "/pessoas",
  "timestamp": "2025-05-14T21:00:00-03:00"
}
```

---

## ✍️ Contribuições

Sinta-se à vontade para abrir issues e propor melhorias! Este projeto é parte do portfólio de [Paulo Castelo](https://github.com/paulocastelo).

---

## 🛡️ Licença

MIT License © 2025

---