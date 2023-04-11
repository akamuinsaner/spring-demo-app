

pipeline {
    agent {
        docker {
            image 'maven:3.9.0-eclipse-temurin-11'
            args '-v /root/.m2:/root/.m2'
        }
    }

    parameters {
        string(name: 'Branch', defaultValue: 'test', description: 'the branch to build')
    }

    stages {
        stage('Git Clone') {
            steps {
                echo "${params.Branch}"
                checkout scm
            }


        }

        stage('Test') {
            steps {
                sh """
                    pwd
                    ls -l
                    mvn -v
                    mvn test
                    
                 """
            }

        }

        stage('Mvn package') {

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
            steps {
                sh """
                    echo deploy
                """
            }
        }
    }
}