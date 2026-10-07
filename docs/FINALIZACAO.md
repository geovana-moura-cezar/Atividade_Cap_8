# Finalizacao da entrega

As configuracoes abaixo dependem das contas reais do grupo e nao devem ser inventadas.

## Docker Hub
1. Crie um repositorio publico chamado `gestao-residuos`.
2. Crie um Access Token.
3. Em GitHub > Settings > Secrets and variables > Actions, crie:
   - `DOCKERHUB_USERNAME`
   - `DOCKERHUB_TOKEN`

## Azure
Crie dois Azure Web Apps Linux para container:
- STAGING
- PRODUCAO

Em cada Web App, configure:
- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`
- `JWT_SECRET`
- `JPA_SHOW_SQL=false`

## GitHub Environments
Crie `staging` e `production`.

Em cada environment:
- Variable: `AZURE_WEBAPP_NAME`
- Secret: `AZURE_WEBAPP_PUBLISH_PROFILE`

Opcionalmente, coloque required reviewer em `production`.

## Pipeline
Depois de configurar os secrets:
1. confira se o CI do Pull Request esta verde;
2. faca merge para `main`;
3. acompanhe `CD - Docker Hub e Azure`;
4. valide staging e producao;
5. tire os prints solicitados.

## Swagger
- Staging: `https://<APP-STAGING>.azurewebsites.net/swagger-ui.html`
- Producao: `https://<APP-PRODUCAO>.azurewebsites.net/swagger-ui.html`

Teste login, use o JWT no botao Authorize e execute um endpoint protegido.

## Seguranca
O projeto original ja teve credenciais gravadas em commits antigos. Remover do codigo atual nao apaga o historico. Rotacione qualquer senha/segredo anteriormente exposto antes de usar staging ou producao.
