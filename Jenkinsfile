

pipeline {
    agent any

    environment {
        branch = 'test';
    }

    stages {
        stage('Git Clone') {
            checkout scm

            sh "pwd && ls -l"
            sh "echo ${branch}"
        }

        stage('Test') {
            sh "mvn test"
        }

        stage('Mvn package') {
            if ("${branch}" == "master") {
                profile = "master"
            } else {
                profile = "test"
            }
            sh "mvn clean package -DskipTests -P${profile}"
        }

        stage('Docker build') {
            sh """
                echo docker build
            """
        }

        stage ('Deploy') {

        }
    }
}