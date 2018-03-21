pipeline {
 environment {
  MAIL = "matias.gonzalez@grupoesfera.com.ar"
  SLACK_CHANNEL = "demo-failed-jobs"
  API_NAME = "demo-api"
  API_CI_URL = "http://192.168.8.162:9090/"

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
    sh 'gradle test'
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
 }