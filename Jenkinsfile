pipeline {
     environment {
          MAIL = "matias.gonzalez@grupoesfera.com.ar"
          SLACK_CHANNEL = "demo-failed-jobs"
    }

    agent {
        docker {
            image 'gradle:4.6.0-jdk8-alpine'
            args '-v $HOME/.gradle:/home/gradle/.gradle'
        }
    }
    stages {
        stage('Build + Unit Test') {

             steps {
                sh 'gradle build'
            }
        }
        stage('Create docker image'){

            steps {
                script{
                    tag = "build-${env.BUILD_NUMBER}" // crear el tag lo asociamos a github?
                }
                sh "gradle -DappVersion=$tag buildImage -x test"
                //push de la imagen
            }
        }
        stage('Deploy CI'){

            steps {
                println "desplegando version $tag"
                //push de la imagen
            }
        }
        stage('Create git tag'){
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    input message:"Crear tag?"
                }

                println "$tag"
            }
        }
    }
    post{
        always {
            publishHTML (target: [
                           allowMissing: false,
                           alwaysLinkToLastBuild: false,
                           keepAll: true,
                           reportDir: 'build/reports/tests/test',
                           reportFiles: 'index.html',
                           reportName: "Test result"
                         ])
            script{
            currentBuild.result='SUCCESS'}
         }
        failure {
               println "enviando mensaje al canal de slack $SLACK_CHANNEL"
               slackSend ( channel:SLACK_CHANNEL,
                            color: '#ff0000',
                            message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")

                emailext (
                  subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                  body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
                  to: MAIL
             )
         }
    }
}