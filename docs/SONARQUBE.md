# Quality gate do SonarQube

Depois de conectar o projeto ao SonarQube, crie/associe um Quality Gate com estas
condições para código novo:

| Métrica | Operador | Limite |
| --- | --- | --- |
| Cobertura | menor que | 85% |
| Bugs críticos | maior que | 0 |
| Security Hotspots revisados | menor que | 100% |
| Dívida técnica | maior que | 1 h |

Cadastre `SONAR_TOKEN` e `SONAR_HOST_URL` nos secrets do GitHub. O job
`quality-gate` chama `sonar:sonar` com `sonar.qualitygate.wait=true`; por isso um
gate reprovado encerra o workflow e impede o job de deploy.
