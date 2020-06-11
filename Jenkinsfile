pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                
                sh '/usr/share/maven/bin clean install'
            }
        }
        stage('Test') {
            steps {
                
                sh '/usr/share/maven/bin test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
