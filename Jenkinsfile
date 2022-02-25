pipeline {
    agent any
   
    stages {
        stage("Test") {
            steps {
                echo "========executing Test========"
                echo "Testing "

                withMaven {
                    sh 'mvn test'
                }
            }
            
        }

    }
  
}
