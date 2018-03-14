 pipeline {
    agent {
        docker {
            image 'gradle:4.6.0-jdk9-alpine'
            args '-v /root/.gradle:/root/.gradle'
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