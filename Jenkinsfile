 pipeline {
    agent {
        docker {
            image 'gradle:4.6.0-jdk8-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'gradle build'
            }
        }
    }
}