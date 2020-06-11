pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh '/usr/share/maven/bin clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
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
