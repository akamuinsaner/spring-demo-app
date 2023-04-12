

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
                    export MAVEN_HOME=$MAVEN_HOME
                    export PATH=$MAVEN_HOME/bin:$PATH
                    mvn --version
                    mvn test
                """

            }

        }

//        stage('Mvn package') {
//
//            steps {
//                if ("${param.Branch}" == "test") {
//                    profile = "test"
//                } else {
//                    profile = ""
//                }
//                sh "mvn clean package -DskipTests -P${profile}"
//            }
//
//        }

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