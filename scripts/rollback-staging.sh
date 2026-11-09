#!/usr/bin/env bash
set -euo pipefail

failed_revision="${1:?Informe a revisão que falhou}"
# Substitua por: kubectl rollout undo deployment/minha-aplicacao --namespace staging
echo "Rollback automático solicitado após falha da revisão: ${failed_revision}"
