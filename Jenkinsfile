

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
            steps {
                sh """
                    echo ${MAVEN_HOME}
                    echo ${JAVA_HOME}
                    echo ${PATH}
                    echo ${env.MAVEN_HOME}
                    echo ${env.JAVA_HOME}
                    echo ${env.PATH}
                    export JAVA_HOME=${env.JAVA_HOME}
                    export MAVEN_HOME=${env.MAVEN_HOME}
                    export PATH=${PATH}:${JAVA_HOME}/bin:${MAVEN_HOME}/bin
                    /usr/bin/mvn test
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