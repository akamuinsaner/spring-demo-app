

node {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }

    def profile

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
                sh 'mvn test'
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