pipeline {
    environment {
        registry ="project4/java"
        dockerHubCreds = 'docker_hub'
        dockerImage = ''
    }
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'echo "Test Branches"'
        withMaven {
            sh 'mvn test'
        }
      }
    }
    stage('Build') {
        when {
            branch 'master'
        }
      steps {
        withMaven {
            sh 'mvn clean package'
        }

      }
    }
    stage('Build Docker Image') {
        when {
            branch 'master'
        }
      steps {
          sh 'echo "Building Docker Image"'
          script {
            dockerImage = docker.build "$registry"

          }
      }
    }
     stage('Delivering Docker Image') {
            when {
                branch 'master'
            }
          steps {
              sh 'echo "Delivering Docker Image"'
              script {
                docker.withRegistry('', dockerHubCreds) {
                    dockerImage.push("$currentBuild.number")
                    dockerImage.push("latest")

                }

              }
          }
        }

  }
}





