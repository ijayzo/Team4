pipeline {
    agent any
   
    stages {
        stage("Test") {
            steps {
                echo "========executing Test========"

                withMaven {
                    sh 'mvn test'
                }
            }
            
        }

    }
  
}
