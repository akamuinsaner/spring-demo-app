

def profile = "test"

pipeline {
    agent {
        docker { image 'eclipse-temurin:17-jdk-alpine' }
    }

    tools {
        maven 'Maven'
    }


    parameters {
        string(name: 'Branch', defaultValue: 'test', description: 'the branch to build')
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
\\
                    mvn test
                """
            }
        }

       stage('Mvn package') {
           steps {
               sh "mvn clean package -DskipTests -P$profile"
           }
       }

        stage('Docker build') {
            steps {
                sh 'docker build --build-arg PROFILE=test -t akamuinsaner/spring-app .'
            }

        }

        stage ('Deploy') {
            steps {
                sh 'echo deploy'
            }
        }
    }
}