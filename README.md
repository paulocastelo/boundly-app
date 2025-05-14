# 📱 boundly-app-api

API REST para ajudar pessoas a manterem o contato com conhecidos, organizando **contatos**, **grupos** e **encontros** de forma leve, eficiente e prática.

---

## 🧱 Tecnologias utilizadas

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Web
- H2 Database (dev)
- PostgreSQL (produção futura)
- Maven

---

## 📦 Estrutura do Projeto

```

com.boundlyapp.api
├── controller       # Endpoints REST
├── model            # Entidades JPA
├── repository       # Interfaces de persistência
├── boundlyAppApiApplication.java

````

---

## 🔧 Como rodar localmente

### Pré-requisitos
- Java 17 ou superior
- Maven
- Eclipse, IntelliJ ou VSCode (com suporte a Maven)

### Passos

```bash
# Clone o repositório
git clone https://github.com/paulocastelo/boundly-app.git

# Acesse o diretório do projeto
cd boundly-app-api

# Rode o projeto
./mvnw spring-boot:run
````

Ou pelo Eclipse:
**Botão direito no arquivo `boundlyAppApiApplication.java` > Run As > Java Application**

---

## 💾 Banco de dados

Utiliza **H2 em memória** para facilitar o desenvolvimento local.

### Console Web:

* Acesse: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
* JDBC URL: `jdbc:h2:mem:boundlydb`
* Usuário: `sa`
* Senha: *(em branco)*

---

## 📡 Endpoints disponíveis (GET)

| Método | Endpoint  | Descrição                |
| ------ | --------- | ------------------------ |
| GET    | /contacts | Lista todos os contatos  |
| GET    | /groups   | Lista todos os grupos    |
| GET    | /meetings | Lista todos os encontros |

---

## 🚀 Próximos passos

* [x] Endpoints GET de listagem
* [ ] Endpoint POST `/contacts` para criar novo contato
* [ ] Validação com Bean Validation
* [ ] Endpoints POST para grupo e meeting
* [ ] Documentação com Swagger/OpenAPI
* [ ] Deploy na nuvem com PostgreSQL

---

## 🧠 Autor

Desenvolvido por **Paulo Anderson Oliveira Castelo**
GitHub: [@paulocastelo](https://github.com/paulocastelo)

````

---