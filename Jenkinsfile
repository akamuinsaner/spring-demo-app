

def profile = "test"

pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
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
            env.JAVA_HOME="${tool 'jdk'}"
            env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
            steps {
                sh """
                    echo "${PATH}"
                    echo "${MAVEN_HOME}"
                    echo "${JAVA_HOME}"
                    echo "${M2_HOME}"
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