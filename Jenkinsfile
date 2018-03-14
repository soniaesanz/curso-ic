 pipeline {
    agent {
        docker {
            image 'gradle:4.6.0-jdk8-alpine'
            args '-v $HOME/.gradle:/home/gradle/.gradle'
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