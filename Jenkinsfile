

def profile = "test"

pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JDK17'
    }


    parameters {
        choices(name: 'BRANCH', choices: ['test', 'master'], description: 'the branch to build')
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
                    docker build --build-arg PROFILE=${profile} -t akamuinsaner/spring-app-${profile} .
                """
            }
        }

        stage('Docker push') {
            when {
                expression { params.BRANCH == 'master' }
            }
            steps {
                sh """
                    docker login --username akamuinsaner --password ElCid_wang0817
                    docker push akamuinsaner/spring-app-${profile}
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