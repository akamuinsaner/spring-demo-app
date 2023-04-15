

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

//        stage('Test') {
//            steps {
//                sh """
//                    mvn -v
//                    mvn test
//                """
//            }
//        }

       stage('Mvn package') {
           steps {
               sh """
                    echo ${env.JAVA_HOME}
                    echo ${env.MAVEN_HOME}
                    echo ${env.PATH}
                    mvn clean package -DskipTests -P$profile
                """
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