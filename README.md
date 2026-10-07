# Projeto - Gestao de Residuos e Reciclagem | Cidades ESG Inteligentes

Projeto ESG desenvolvido em Java/Spring Boot e evoluido nesta fase com praticas de DevOps: testes automatizados, containerizacao, Docker Compose, GitHub Actions e deploy em dois ambientes no Azure.

## Sobre o projeto

A API apoia o gerenciamento de residuos e reciclagem, permitindo trabalhar com usuarios, residuos, locais de coleta, descartes, coletas e alertas. O projeto-base foi preservado e recebeu uma camada DevOps para automatizar build, testes e promocao entre ambientes.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT
- Bean Validation
- Oracle Database
- Flyway
- H2 para testes do CI
- JUnit 5 e Mockito
- Swagger / OpenAPI
- Maven
- Docker e Docker Compose
- Docker Hub
- GitHub Actions
- Azure App Service

## Estrutura

```text
.
├── .github/
│   └── workflows/
│       ├── ci.yml
│       └── cd.yml
├── docs/
│   ├── EVIDENCIAS.md
│   └── FINALIZACAO.md
├── gestao_residuos/
│   ├── src/
│   ├── .env.example
│   ├── Dockerfile
│   ├── docker-compose.yml
│   └── pom.xml
└── README.md
```

## Como executar localmente com Docker

Entre na pasta da aplicacao:

```bash
cd gestao_residuos
```

Copie o arquivo de exemplo:

```bash
cp .env.example .env
```

No PowerShell:

```powershell
Copy-Item .env.example .env
```

Suba a API e o Oracle:

```bash
docker compose up --build -d
```

Confira:

```bash
docker compose ps
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

Logs:

```bash
docker compose logs -f gestao-residuos
```

Encerrar:

```bash
docker compose down
```

O Compose utiliza **variaveis de ambiente**, **volumes nomeados** e uma **rede bridge dedicada**, atendendo aos requisitos da atividade.

## Swagger / OpenAPI

A documentacao interativa da API fica em:

```text
http://localhost:8080/swagger-ui.html
```

A especificacao OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

O Swagger possui esquema Bearer/JWT. Depois do login, o token pode ser informado no botao **Authorize** para testar os endpoints protegidos.

## Testes automatizados

O projeto possui teste de contexto Spring e testes unitarios do `ResiduoService` com JUnit e Mockito.

Execucao local:

```bash
cd gestao_residuos
./mvnw clean test
```

No CI:

```bash
./mvnw -B clean verify
```

Os testes usam H2 em memoria, evitando que o runner do GitHub dependa do Oracle externo apenas para validar o build.

## Pipeline CI/CD

### CI - Build e Testes

Arquivo: `.github/workflows/ci.yml`.

O CI roda em Pull Requests para `main` e `develop` e em pushes para `develop`.

Etapas:

1. checkout do codigo;
2. configuracao do Java 21;
3. build e testes com Maven;
4. validacao do Docker Compose;
5. build da imagem Docker;
6. publicacao dos relatorios de teste como artefato.

### CD - Docker Hub e Azure

Arquivo: `.github/workflows/cd.yml`.

O CD roda quando o codigo aprovado chega a `main`.

Etapas:

1. build e testes;
2. build da imagem Docker;
3. push no Docker Hub com tag do SHA do commit e `latest`;
4. deploy da imagem em **staging**;
5. smoke test em `/v3/api-docs`;
6. deploy da **mesma imagem** em **producao**;
7. smoke test de producao.

A mesma tag imutavel do commit e promovida entre staging e producao, evitando recompilar um artefato diferente para o ambiente final.

## Containerizacao

O Dockerfile utiliza **multi-stage build**:

- primeiro estagio: JDK 21 + Maven Wrapper para gerar o JAR;
- segundo estagio: JRE 21 Alpine para executar a aplicacao;
- usuario nao-root;
- healthcheck HTTP em `/v3/api-docs`.

O Docker Compose orquestra:

- `oracle-db`: Oracle Database Free para desenvolvimento local;
- `gestao-residuos`: API Spring Boot.

Volumes:

- `oracle-data`: persistencia dos dados locais;
- `app-logs`: persistencia dos logs.

Rede:

- `gestao-residuos-network`.

## Variaveis e secrets

O projeto nao deve versionar credenciais reais.

### GitHub Repository Secrets

- `DOCKERHUB_USERNAME`
- `DOCKERHUB_TOKEN`

### Environment `staging`

Variable:
- `AZURE_WEBAPP_NAME`

Secret:
- `AZURE_WEBAPP_PUBLISH_PROFILE`

### Environment `production`

Variable:
- `AZURE_WEBAPP_NAME`

Secret:
- `AZURE_WEBAPP_PUBLISH_PROFILE`

### Azure App Settings

Em cada Web App:

- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`
- `JWT_SECRET`
- `JPA_SHOW_SQL=false`

As instrucoes exatas estao em `docs/FINALIZACAO.md`.

## Ambientes

### Staging

Preencher depois da criacao do recurso:

```text
https://<APP-STAGING>.azurewebsites.net
https://<APP-STAGING>.azurewebsites.net/swagger-ui.html
```

### Producao

Preencher depois da criacao do recurso:

```text
https://<APP-PRODUCAO>.azurewebsites.net
https://<APP-PRODUCAO>.azurewebsites.net/swagger-ui.html
```

## Prints do funcionamento

Os prints reais devem ser incluidos somente depois que o pipeline e os ambientes forem executados. O roteiro esta em `docs/EVIDENCIAS.md`.

Evidencias previstas:

1. CI com build e testes aprovados;
2. build/push da imagem Docker;
3. deploy em staging;
4. Swagger de staging;
5. deploy em producao;
6. Swagger de producao;
7. Docker Compose local (recomendado).

## Desafios encontrados e solucoes

### Credenciais no projeto-base

**Desafio:** configuracoes sensiveis estavam gravadas diretamente no projeto anterior.

**Solucao:** uso de variaveis de ambiente, `.env.example`, `.gitignore`, GitHub Secrets e Azure App Settings. Credenciais que ja apareceram no historico devem ser rotacionadas.

### Testes dependentes de infraestrutura externa

**Desafio:** o teste de contexto poderia depender do Oracle remoto.

**Solucao:** H2 em memoria no escopo de testes, mantendo Oracle na aplicacao real.

### Diferencas entre ambientes

**Desafio:** garantir que desenvolvimento, staging e producao usem um runtime previsivel.

**Solucao:** Docker multi-stage, Docker Compose e imagem versionada pelo SHA do commit.

### Promocao para producao

**Desafio:** evitar gerar um artefato diferente depois da validacao de staging.

**Solucao:** promover para producao exatamente a mesma imagem Docker validada em staging.

## Checklist obrigatorio

Marcar os itens somente depois de validar as evidencias reais.

| Item | OK |
|---|:---:|
| Projeto compactado em .ZIP com estrutura organizada | ☐ |
| Dockerfile funcional | ☐ |
| docker-compose.yml ou arquivos Kubernetes | ☐ |
| Pipeline com etapas de build, teste e deploy | ☐ |
| README.md com instrucoes e prints | ☐ |
| Documentacao tecnica com evidencias (PDF ou PPT) | ☐ |
| Deploy realizado nos ambientes staging e producao | ☐ |

## O que falta para finalizar

Siga, na ordem:

1. `docs/FINALIZACAO.md`;
2. execute o pipeline real;
3. salve os prints descritos em `docs/EVIDENCIAS.md`;
4. substitua os placeholders de URL;
5. anexe as evidencias ao README e ao PDF final;
6. marque o checklist;
7. gere o ZIP final e confira antes do upload.
