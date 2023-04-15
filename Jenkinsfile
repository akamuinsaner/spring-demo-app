pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        PROFILE = "${params.BRANCH == "master" ? "prod" : "test"}"
        PREFIX = "spring-app"
        PROJECT_NAME = 'akamuinsaner'
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
                    docker build --build-arg PROFILE=${env.PROFILE} -t ${env.PROJECT_NAME}/${env.PREFIX}-${env.PROFILE} .
                """
            }
        }

        stage('Docker push') {
            when {
                expression { params.BRANCH == 'master' }
            }
            steps {
                withCredentials([string(credentialsId: 'hub.docker', passwordVariable: 'password', usernameVariable: 'username')]) {
                    sh """
                        docker login --username ${username} --password ${password}
                        docker push ${env.PROJECT_NAME}/${env.PREFIX}-${env.PROFILE}
                        docker rmi ${env.PROJECT_NAME}/${env.PREFIX}-${env.PROFILE}
                    """
                }

            }
        }

        stage ('Deploy') {
            steps {
                sh """
                   docker pull ${env.PROJECT_NAME}/${env.PREFIX}-${env.PROFILE}
                   docker run -p 8443:8888 ${env.PROJECT_NAME}/${env.PREFIX}-${env.PROFILE}:latest
                """
            }
        }
    }
}