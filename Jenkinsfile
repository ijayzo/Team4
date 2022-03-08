pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'echo "Test Branches"'
        withMaven() {
          sh 'mvn test'
        }

      }
    }

    stage('Build') {
      when {
        branch 'master'
      }
      steps {
        withMaven() {
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
    stage('Wait for approval to Deploy to Production') {
            when {
                branch 'main'
            }
            steps {
                script {
                    try {
                        timeout(time: 1, unit: 'MINUTES') {
                            approved = input message: 'Deploy to production?', ok: 'Continue',
                                               parameters: [choice(name: 'approved', choices: 'Yes\nNo', description: 'Deploy build to production')]
                            if(approved != 'Yes') {
                                error('Build did not pass approval')
                            }
                        }
                    } catch(error) {
                        error('Build failed because timeout was exceeded')
                    }
                }
            }
        }

    stage('Deploy into Kubernetes') {
      when {
        branch 'master'
      }
      steps {
        sh 'echo "Starting Deployment to Kubernetes"'
        sh 'sed -i "s/%TAG%/$BUILD_NUMBER/g" ./Kubernetes/deployment.yml'
        sh 'cat ./Kubernetes/deployment.yml'
        step([$class: 'KubernetesEngineBuilder',
                              projectId: 'united-button-342103',
                              clusterName: 'pogi-firstcluster',
                              zone: 'us-central1-a',
                              manifestPattern: 'Kubernetes/',
                              credentialsId: 'united-button-342103',
                              verifyDeployments: true
                        ])
      }
    }


  }
  environment {
    registry = 'project2team4/java'
    dockerHubCreds = 'Docker_Credential'
    dockerImage = ''
    deploymentFile = 'Kubernetes/deployment.yml'
  }
}