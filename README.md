# Atividade 10 - CI/CD com qualidade

Implementação do desafio integrado da aula. O workflow em `.github/workflows/ci.yml`
executa testes unitários e de integração em paralelo, bloqueia o fluxo por cobertura
menor que 85%, regras PMD ou vulnerabilidades OWASP e só libera o deploy após aprovação.

## Como validar localmente

```bash
mvn test
mvn verify -Pintegration-tests
mvn verify
mvn org.owasp:dependency-check-maven:check
```

## Configuração necessária no GitHub

1. Em **Settings > Environments > staging**, crie o ambiente `staging` e adicione
   os revisores obrigatórios. É essa proteção que transforma o job de deploy em uma
   aprovação manual.
   Em **Settings > Branches**, exija os checks `Unit tests`, `Integration tests` e
   `Coverage, PMD, OWASP and SonarQube` na branch `main`, para impedir o merge de
   PRs reprovados.
2. Para habilitar o SonarQube, cadastre `SONAR_TOKEN` e `SONAR_HOST_URL` nos secrets
   do repositório e aplique as regras descritas em `docs/SONARQUBE.md`. Quando ambos
   existirem, o workflow espera o quality gate do Sonar.

O `Jenkinsfile` oferece o mesmo fluxo, incluindo a aprovação com `input` e rollback
automático se o estágio de deploy falhar.
