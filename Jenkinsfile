

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
                checkout scmGit(branches: [[name: '*/test']], extensions: [], userRemoteConfigs: [[credentialsId: '2d6ec0c8-2cca-456d-8eb7-0d9017a125e5', url: 'https://github.com/akamuinsaner/spring-demo-app.git']])
            }


        }

        stage('Test') {
            steps {
                sh "mvn test"
            }

        }

        stage('Mvn package') {
            if ("${branch}" == "master") {
                profile = "master"
            } else {
                profile = "test"
            }
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

        }
    }
}