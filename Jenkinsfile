node {

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
            checkout scm
        }

        stage('Test') {
            sh """
                    mvn test
                """
        }

       stage('Mvn package') {
           sh "mvn clean package -DskipTests -P${env.PROFILE}"
       }

        stage('Docker build') {
            sh """
                    docker build --build-arg PROFILE=${env.PROFILE} -t ${env.PROJECT_NAME}/${env.JOB_NAME}-${env.PROFILE}:${env.BUILD_ID} .
                """
        }

        stage('Docker push') {
            when {
                expression { params.BRANCH == 'master' }
            }
            withCredentials([string(credentialsId: 'hub.docker', passwordVariable: 'password', usernameVariable: 'username')]) {
                sh """
                        docker login --username ${username} --password ${password}
                        docker push ${env.PROJECT_NAME}/${env.JOB_NAME}-${env.PROFILE}:${env.BUILD_ID}

                    """
            }
        }

        stage ('Deploy') {

            CONTAINER_ID = sh(script: "docker ps -a | grep -0e ${env.PROJECT_NAME}/${env.JOB_NAME}-${env.PROFILE} | cut -c1-10", returnStdout: true)
            sh """
                     echo ${CONTAINER_ID}
                     docker stop ${CONTAINER_ID}
                     echo ${env.PROJECT_NAME}/${env.JOB_NAME}-${env.PROFILE}:${env.BUILD_ID}
                     docker run -d -p 8443:8888 ${env.PROJECT_NAME}/${env.JOB_NAME}-${env.PROFILE}:${env.BUILD_ID}
                 """
        }
    }
}