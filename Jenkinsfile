pipeline {
    agent any

    environment {
        SONAR_TOKEN = 'squ_81f6b964f46cafcada8aa96e7f9e96912b9f2b31' // Ensure the token is securely stored if possible
    }

    stages {
       stage('Checkout') {
            steps {
                git branch: 'DhikraBenMahmoud-5DS7-G3',
                    url: 'https://github.com/Hend-10/5DS7_G3_TpFoyer.git',
                    credentialsId: 'git-token'
            }
        }

        stage('MVN CLEAN') {
            steps {
                echo 'Running Maven clean...'
                sh 'mvn clean'
            }
        }

        stage('Build Backend') {
            steps {
                dir('Backend') {
                    echo 'Building backend...'
                    sh 'mvn clean package -X'   // Run Maven package with debug info
                    echo 'Listing target directory contents...'
                    sh 'ls -la target/'         // List the contents of the target directory
                }
            }
        }

        stage('MVN COMPILE') {
            steps {
                echo 'Compiling the project...'
                sh 'mvn compile'
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                script {
                    echo 'Running SonarQube analysis...'
                    withSonarQubeEnv('Sonarqube') {
                        sh "mvn sonar:sonar -Dsonar.login=${SONAR_TOKEN}"
                    }
                }
            }
        }
    }
}
