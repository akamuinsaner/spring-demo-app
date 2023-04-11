

pipeline {
    agent any

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