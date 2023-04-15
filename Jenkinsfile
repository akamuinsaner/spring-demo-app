

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
                sh """
                    docker build --build-arg PROFILE=${profile} -t akamuinsaner/spring-app:${profile} .
                """
            }
        }

        stage('Docker push') {
            steps {
                sh """
                    docker login https://hub.docker.com/ -u akamuinsaner -p ElCid_wang0817
                    docker push akamuinsaner/spring-app:${profile}
                """
            }
        }

        stage ('Deploy') {
            steps {
                sh 'echo deploy'
            }
        }
    }
}