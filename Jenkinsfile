pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        PROFILE = "${params.BRANCH == "master" ? "prod" : "test"}"
        PROJECT_NAME = 'akamuinsaner'
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
               sh "mvn clean package -DskipTests -P${env.PROFILE}"
           }
       }

        stage('Docker build') {
            steps {
                sh """
                    docker build --build-arg PROFILE=${env.PROFILE} -t ${env.PROJECT_NAME}/${env.JOB_NAME}-${env.PROFILE}:${env.BUILD_ID} .
                """
            }
        }

        stage('Docker push') {
            when {
                expression { params.BRANCH == 'master' }
            }
            steps {
                withCredentials([string(credentialsId: 'hub.docker', passwordVariable: 'password', usernameVariable: 'username')]) {
                    sh """
                        docker login --username ${username} --password ${password}
                        docker push ${env.PROJECT_NAME}/${env.JOB_NAME}-${env.PROFILE}:${env.BUILD_ID}

                    """
                }

            }
        }

        stage ('Deploy') {

            steps {
                script {
                    CONTAINER_ID = sh script: "docker ps -a | grep -0e ${env.PROJECT_NAME}/${env.JOB_NAME}-${env.PROFILE} | cut -c1-10", returnStdout: true
                    P_NAME = "${env.PROJECT_NAME}"
                    J_NAME = "${env.JOB_NAME}"
                    P = "${env.PROFILE}"
                    B = "${env.BUILD_ID}"
                }
                sh """
                     echo ${CONTAINER_ID}
                     docker stop ${CONTAINER_ID}
                     echo ${env.PROJECT_NAME}/${env.JOB_NAME}-${env.PROFILE}:${env.BUILD_ID}
                     docker run -d -p 8443:8888 ${env.P_NAME}/${env.J_NAME}-${env.P}:${B}
                 """
            }
        }
    }
}