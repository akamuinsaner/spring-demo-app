

def profile = "test"

pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        JAVA_HOME = "${ tool 'JDK17' }"
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
                    echo "${PATH}"
                    echo "${MAVEN_HOME}"
                    echo "${JAVA_HOME}"
                    echo "${M2_HOME}"
                    java -version
                    export JAVA_HOME=${JAVA_HOME}
                    export PATH=${JAVA_HOME}/bin:${PATH}
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