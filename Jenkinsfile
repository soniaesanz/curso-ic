pipeline {
 environment {
  MAIL = "matias.gonzalez@grupoesfera.com.ar"
  SLACK_CHANNEL = "demo-failed-jobs"
  API_NAME = "demo-api"
  API_CI_URL = "http://192.168.8.162:9090/"
  SONAR_URL = "http://192.168.8.162:9000"

 }
 agent any

 stages {

  stage('Unit Test + sonar') {
   agent {
    docker {
     image 'gradle:4.6.0-jdk8-alpine'
     args '-v $HOME/.gradle:/home/gradle/.gradle'
    }
   }

   steps {
    sh 'gradle test'
    sh "gradle -Dsonar.host.url='${env.SONAR_URL}' sonarqube ""

    junit 'build/test-results/test/*.xml'
   }
  }
  stage('Create docker image') {

   agent {
    docker {
     image 'gradle:4.6.0-jdk8-alpine'
     args '-v $HOME/.gradle:/home/gradle/.gradle'
    }
   }
   steps {
    script {
     env.VERSION = env.BRANCH_NAME == "master" ? "build-${env.BUILD_NUMBER}" : "latest"
     sh "gradle -DappVersion=${env.VERSION} -DapiName=${env.API_NAME} buildImage -x test"
    }
   }
  }
  stage('Deploy CI') {

   steps {
    sh "sh deploy-ci.sh ${env.API_NAME} ${env.VERSION}"
   }
  }
  stage('Integration Test') {

   steps {

    sh "docker run --rm -v $WORKSPACE/postman-collection:/home/groovy/script -w /home/groovy/script groovy:latest groovy wait-ic.groovy ${env.API_CI_URL}"
    sh 'sh postman-collection/run-integration.sh'
   }
  }

  stage('Merge to Staging') {
   when {
    branch 'develop'
   }
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
      timeout(time: 60, unit: 'SECONDS') {
       input message: 'Do you want to merge?',
        parameters: [
         [$class: 'BooleanParameterDefinition',
          defaultValue: false,
          description: '',
          name: 'Release'
         ]
        ]
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
 post {
  always {
   junit 'postman-collection/newman/*.xml'
  }
  failure {



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