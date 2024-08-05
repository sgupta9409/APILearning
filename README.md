pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }

    stages {
        stage('Stage 1: Checkout') {
            steps {
                // Get some code from a GitHub repository
                git credentialsId: 'e873f81a-1517-4da0-b495-30610cf2adfa', url: 'https://github.com/sgupta9409/APILearning.git'
            }
        }
        
        stage('Stage 2: Build') {
            steps {
                // Get some code from a GitHub repository
                bat 'mvn clean'
            }
        }
        
        stage('Stage 3: Compile Code') {
            steps {
                // Get some code from a GitHub repository
                bat 'mvn compile'
            }
        }
        
        stage('Stage 4: Run Code') {
            steps {
                // Get some code from a GitHub repository
                bat 'mvn test -Dfoldername=APIs -Denvname=stage'
            }
        }
    }
    
    post{
        always{
                emailext (to: 'guptashubhamumb@gmail.com', replyTo: 'guptashubhamumb@gmail.com', subject: "Email Report from - '${env.JOB_NAME}' ", body: readFile("target/surefire-reports/emailable-report.html"), mimeType: 'text/html', attachmentsPattern: 'target/surefire-reports/emailable-report.html');
        }
    }
}
