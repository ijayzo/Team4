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

        stage("Build") {
            echo "This is building"
        }
    }
  
}
