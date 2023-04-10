

pipeline {
    agent any

    environment {
        branch = 'test';
    }

    stages {
        stage('Git Clone') {
            steps {

                sh "pwd && ls -l"
                sh "echo ${branch}"
                checkout scmGit(branches: [[name: '*/test']], extensions: [], userRemoteConfigs: [[credentialsId: 'tenxuncloud', url: 'https://github.com/akamuinsaner/spring-demo-app.git']])
            }


        }

        stage('Test') {
            steps {
                sh "mvn test"
            }

        }

        stage('Mvn package') {

            steps {

                sh "mvn clean package -DskipTests -P${profile}"
            }

        }

        stage('Docker build') {
            steps {
                sh """
                    echo docker build
                """
            }

        }

        stage ('Deploy') {
            steps {
                sh """
                    echo deploy
                """
            }
        }
    }
}