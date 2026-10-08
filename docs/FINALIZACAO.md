# Finalizacao da entrega

As configuracoes abaixo dependem das contas reais do grupo e nao devem ser versionadas.

## Docker Hub
1. Repositorio publico: `gestao-residuos`.
2. GitHub Repository Secrets:
   - `DOCKERHUB_USERNAME`
   - `DOCKERHUB_TOKEN`

## Render
Foram criados dois Web Services a partir da imagem publica do Docker Hub:

- STAGING: `https://gestao-residuos-staging.onrender.com`
- PRODUCAO: `https://gestao-residuos-production.onrender.com`

Em cada Web Service:
- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`
- `JWT_SECRET`
- `JPA_SHOW_SQL=false`
- `FLYWAY_VALIDATE_ON_MIGRATE=false`

Health Check Path:
- `/v3/api-docs`

A validacao do Flyway foi desativada apenas nesses ambientes porque o banco academico ja possuia migrations aplicadas cujos arquivos V6 e V8 tiveram checksum alterado. Em um ambiente real, migrations ja aplicadas nao devem ser editadas.

## GitHub Environments
Foram criados os environments `staging` e `production`.

Em cada environment:

Variable:
- `RENDER_SERVICE_URL`

Secret:
- `RENDER_DEPLOY_HOOK`

Nao habilitar required reviewer se a demonstracao exigir promocao totalmente automatica entre os ambientes.

## Pipeline
Fluxo esperado depois do merge para `main`:

1. build e testes;
2. build da imagem Docker;
3. push no Docker Hub com tag do SHA e `latest`;
4. acionamento do Deploy Hook de staging;
5. smoke test em `/v3/api-docs`;
6. acionamento do Deploy Hook de producao;
7. smoke test de producao.

## Swagger
- Staging: `https://gestao-residuos-staging.onrender.com/swagger-ui/index.html`
- Producao: `https://gestao-residuos-production.onrender.com/swagger-ui/index.html`

## Seguranca
Credenciais reais nao devem aparecer no repositorio, em prints ou na documentacao. Como o projeto-base ja teve credenciais em commits antigos, senhas e segredos anteriormente expostos devem ser rotacionados.
