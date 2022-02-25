pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'echo "Test is running good"'
      }
    }
    stage('Build') {
      steps {
        sh 'echo "I am in the Build, Change Directory"'
      }
    }
    stage('MavenTest') {
      steps {
          withMaven {
            sh 'mvn test'
        }
      }
    }

  }
}


 

  