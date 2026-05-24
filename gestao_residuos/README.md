# ♻️ Gestão de Resíduos e Reciclagem - API RESTful

## 📌 Sobre o Projeto

Este projeto foi desenvolvido como atividade avaliativa da disciplina de Java Advanced utilizando Spring Boot.

A aplicação tem como objetivo auxiliar no gerenciamento de resíduos e reciclagem, promovendo práticas sustentáveis alinhadas ao tema ESG.

O sistema oferece recursos para:

* Cadastro e autenticação de usuários
* Controle de resíduos recicláveis
* Cadastro de locais de coleta
* Controle de coletas seletivas
* Registro de descartes
* Geração de alertas
* Segurança com autenticação JWT
* Controle de acesso por perfis (USER e ADMIN)

A API foi construída seguindo os princípios RESTful, utilizando Spring Security, Oracle Database, Flyway e Docker.

---

# 🌱 Tema ESG Escolhido

## Gestão de Resíduos e Reciclagem

### Objetivos do sistema

* Incentivar o descarte correto de resíduos
* Monitorar coletas seletivas
* Registrar descartes realizados
* Gerar alertas automáticos
* Auxiliar práticas sustentáveis
* Melhorar a gestão ambiental

---

# 🛠️ Tecnologias Utilizadas

## Backend

* Java 21
* Spring Boot 4
* Spring Web
* Spring Data JPA
* Spring Security
* JWT Authentication
* Maven

## Banco de Dados

* Oracle Database

## Migração de Banco

* Flyway

## Conteinerização

* Docker
* Docker Compose

---

# 📂 Estrutura do Projeto

```text
src
├── main
│   ├── java
│   │   └── br.com.fiap.gestao_residuos
│   │       ├── controller
│   │       ├── service
│   │       ├── repository
│   │       ├── model
│   │       ├── dto
│   │       ├── config
│   │       └── exception
│   └── resources
│       ├── db
│       │   └── migration
│       └── application.properties
```

---

# 🔐 Segurança da Aplicação

A aplicação utiliza:

* Spring Security
* JWT Token
* BCrypt Password Encoder
* Autenticação Stateless
* Controle de acesso por perfil

## Perfis de acesso

### USER

Pode:

* Realizar login
* Consultar informações da API

### ADMIN

Pode:

* Cadastrar registros
* Atualizar registros
* Excluir registros

## Fluxo de autenticação

1. Usuário realiza login
2. API gera um token JWT
3. O token é enviado nas requisições protegidas
4. A API valida o token antes de liberar acesso

---

# 🗄️ Banco de Dados

O projeto utiliza Oracle Database como banco principal.

As tabelas são criadas e versionadas automaticamente utilizando Flyway.

## Principais entidades

* Usuário
* Resíduo
* Coleta
* Local de Coleta
* Descarte
* Alerta

---

# 🚀 Como Executar o Projeto

## Pré-requisitos

* Java 21
* Maven
* Docker Desktop
* Git

---

# ▶️ Executando Localmente

## 1. Extrair os arquivos da pasta .zip

## 2. Entrar na pasta do projeto

```bash
cd gestao_residuos
```

## 3. Gerar a build do projeto

```bash
mvn clean package
```

## 4. Executar a aplicação

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em:

```text
http://localhost:8080
```

---

# 🐳 Executando com Docker

## 1. Gerar o build do projeto

```bash
mvn clean package
```

## 2. Executar o Docker Compose

```bash
docker compose up --build
```

A API ficará disponível em:

```text
http://localhost:8080
```

---

# 📌 Endpoints Principais

## 🔐 Autenticação

| Método | Endpoint       | Descrição                    |
| ------ | -------------- | ---------------------------- |
| POST   | /auth/register | Cadastro de usuário          |
| POST   | /auth/login    | Login e geração do token JWT |

---

## 👤 Usuários

| Método | Endpoint      |
| ------ | ------------- |
| GET    | /api/usuarios |
| GET    | /api/usuarios/{id} |
| PUT    | /api/usuarios |
| DELETE | /api/usuarios/{id} |

---

## ♻️ Resíduos

| Método | Endpoint      |
| ------ | ------------- |
| GET    | /api/residuos |
| GET    | /api/residuos/{id} |
| POST   | /api/residuos |
| PUT    | /api/residuos |
| DELETE | /api/residuos/{id} |

---

## 🚛 Coletas

| Método | Endpoint    |
| ------ | ----------- |
| GET    | /api/coletas |
| GET    | /api/coletas/{id} |
| POST   | /api/coletas |
| PUT    | /api/coletas|
| DELETE | /api/coletas/{id} |

---

## 📍 Locais de Coleta

| Método | Endpoint          |
| ------ | ----------------- |
| GET    | /api/local-coleta |
| GET    | /api/local-coleta/{id} |
| POST   | /api/local-coleta |
| PUT    | /api/local-coleta |
| DELETE | /api/local-coleta/{id} |

---

## 🗑️ Descartes

| Método | Endpoint       |
| ------ | -------------- |
| GET    | /api/descartes |
| GET    | /api/descartes/{id} |
| POST   | /api/descartes |
| PUT    | /api/descartes |
| DELETE | /api/descartes/{id} |

---

## 🚨 Alertas

| Método | Endpoint      |
| ------ | ------------- |
| GET    | /api/alertas  |
| GET    | /api/alertas/{id} |

---

# 🧪 Testes da API

Os testes da API podem ser realizados utilizando:

* Insomnia
* Postman

A collection da API acompanha a entrega do projeto.

---

# ⚠️ Tratamento de Exceções

A aplicação possui tratamento global de exceções utilizando:

* @RestControllerAdvice
* Validação de DTOs
* Respostas padronizadas
* Tratamento de erros de validação
* Tratamento de recursos não encontrados
* Tratamento de erros internos

---

# 📈 Requisitos Atendidos

✅ API RESTful

✅ Mínimo de 4 endpoints

✅ Spring Boot

✅ Spring Security

✅ JWT

✅ Oracle Database

✅ Flyway

✅ Docker

✅ Validação de dados

✅ Tratamento de exceções

✅ Arquitetura em camadas

✅ Controle de acesso por perfil

---

# 👨‍💻 Desenvolvido por

Projeto desenvolvido para fins acadêmicos na FIAP.
