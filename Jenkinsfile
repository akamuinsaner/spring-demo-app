pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        PROFILE = "${params.BRANCH == "master" ? "prod" : "test"}"
    }

    stages {
        stage('Git Clone') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh """
                    mvn test
                """
            }
        }

       stage('Mvn package') {
           steps {
               sh "mvn clean package -DskipTests -P${env.PROFILE}"
           }
       }

        stage('Docker build') {
            steps {
                sh """
                    docker build --build-arg PROFILE=${env.PROFILE} -t akamuinsaner/spring-app-${env.PROFILE} .
                """
            }
        }

        stage('Docker push') {
            when {
                expression { params.BRANCH == 'master' }
            }
            steps {
                sh """
                    docker login --username akamuinsaner --password ElCid_wang0817
                    docker push akamuinsaner/spring-app-${env.PROFILE}
                """
            }
        }

        stage ('Deploy') {
            steps {
                sh 'echo deploy'
            }
        }
    }
}