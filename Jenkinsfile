

def profile = "test"

pipeline {
    agent any

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
                    echo ${PATH}
                    echo ${env.MAVEN_HOME}
                    echo ${env.PATH}
                    export MAVEN_HOME=${env.MAVEN_HOME}
                    export PATH=${MAVEN_HOME}/bin:${PATH}
                    mvn test
                """
            }
        }

       stage('Mvn package') {
           if ("${param.Branch}" == "test") {
               profile = "test"
           } else {
               profile = "prod"
           }
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