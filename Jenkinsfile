pipeline {
     environment {
          MAIL = "matias.gonzalez@grupoesfera.com.ar"
          SLACK_CHANNEL = "demo-failed-jobs"
    }

   agent any
    stages {
        stage('Build + Unit Test') {
          agent {
                 docker {
                     image 'gradle:4.6.0-jdk8-alpine'
                     args '-v $HOME/.gradle:/home/gradle/.gradle'
                 }
             }
             steps {
                sh 'gradle build'
            }
        }
        stage('Create docker image'){
             agent {
                    docker {
                        image 'gradle:4.6.0-jdk8-alpine'
                        args '-v $HOME/.gradle:/home/gradle/.gradle'
                    }
                }
            steps {
               sh "gradle -DappVersion=latest buildImage -x test"
            }
        }
        stage('Deploy CI'){

            steps {
                println "desplegando en CI la ultima version"
                //push de la imagen
            }
        }
        stage('Integration Test'){

            steps {
                println "Corriendo newman"
                //push de la imagen
            }
        }
        stage('Merge to Staging'){
             agent {
                    docker {
                        image 'alpine/git'
                        args '-v $HOME:/home/build'
                    }
                }
            steps {

               script {
                   result = null
                   try {
                    timeout(time:60, unit:'SECONDS') {
                        input message: 'Do you want to merge?',
                              parameters: [[$class: 'BooleanParameterDefinition',
                                            defaultValue: false,
                                            description: '',
                                            name: 'Release']]
                    }

                    sh "git tag  build-${env.BUILD_NUMBER}"

                } catch (err) {
                    result = false
                    println "Timeout for merge   reached"
                    }
               }
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