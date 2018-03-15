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
    post{
        always {
            junit '**/build/test-results/test/*.xml'
         }
        failure {
                slackSend (color: '#ff0000', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")

                emailext (
                  subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                  body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
                  to: "matias.gonzalez@grupoesfera.com.ar"
             )
         }
    }
}