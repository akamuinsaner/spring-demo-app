

def profile = "test"

pipeline {
    agent any

    parameters {
        string(name: 'Branch', defaultValue: 'test', description: 'the branch to build')
    }

    tools {
        maven 'Maven 3.9.1'
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
                    echo ${MAVEN_HOME}
                    echo ${PATH}
                    echo ${env.MAVEN_HOME}
                    echo ${env.PATH}
                    export MAVEN_HOME=${env.MAVEN_HOME}
                    export PATH=${PATH}:${MAVEN_HOME}/bin
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