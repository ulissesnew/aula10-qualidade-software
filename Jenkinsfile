pipeline {
  agent any

  tools { jdk 'jdk17'; maven 'maven3' }

  stages {
    stage('Build') {
      steps { sh 'mvn --batch-mode clean compile' }
    }
    stage('Tests in parallel') {
      parallel {
        stage('Unit tests') {
          steps { sh 'mvn --batch-mode test' }
          post { always { junit 'target/surefire-reports/*.xml' } }
        }
        stage('Integration tests') {
          steps { sh 'mvn --batch-mode verify -Pintegration-tests' }
          post { always { junit 'target/failsafe-reports/*.xml' } }
        }
      }
    }
    stage('Quality gate') {
      steps {
        sh 'mvn --batch-mode verify pmd:check'
        sh 'mvn --batch-mode org.owasp:dependency-check-maven:check'
      }
    }
    stage('Deploy staging') {
      when { branch 'main' }
      steps {
        input message: 'Aprovar deploy em staging?', ok: 'Deploy'
        sh './scripts/deploy-staging.sh "$GIT_COMMIT"'
      }
      post {
        failure { sh './scripts/rollback-staging.sh "$GIT_COMMIT"' }
      }
    }
  }

  post {
    always { archiveArtifacts artifacts: 'target/site/jacoco/**,target/dependency-check-report.*', allowEmptyArchive: true }
    failure { echo "Pipeline falhou: ${env.JOB_NAME} #${env.BUILD_NUMBER}" }
  }
}
