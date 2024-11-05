pipeline {
    agent any

         parameters {
        string(name: 'NEXUS_URL', defaultValue: '192.168.50.4:8081', description: 'Nexus URL')
        string(name: 'NEXUS_REPOSITORY', defaultValue: 'maven-releases', description: 'Nexus Repository Name')
         }

    environment {
        SONAR_TOKEN = 'squ_81f6b964f46cafcada8aa96e7f9e96912b9f2b31' // Ensure the token is securely stored if possible
        NEXUS_CREDENTIALS = credentials('nexusToken')
        MAVEN_REPO_ID = 'nexus'
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
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
        dir('Backend') {  // Adjust the path if your pom.xml is not in this directory
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




        stage('Upload Artifact to Nexus') {
    steps {
        echo "NEXUS_URL: ${NEXUS_URL}"
        script {
            // Define artifact details based on the known pom.xml values
            def groupId = "tn.esprit"
            def artifactId = "tp-foye"
            def version = "5.0.0"
            def packaging = "jar"
            def artifactPath = "target/tp-foyer-5.0.0.jar"
            def pomFile = "pom.xml"

            // Check if the artifact exists
            if (fileExists(artifactPath)) {
                echo "*** File: ${artifactPath}, group: ${groupId}, packaging: ${packaging}, version ${version}"

                // Upload artifact and POM to Nexus
                nexusArtifactUploader(
                    nexusVersion: NEXUS_VERSION,
                    protocol: NEXUS_PROTOCOL,
                    nexusUrl: NEXUS_URL,
                    groupId: groupId,
                    artifactId: artifactId,
                    version: version,
                    repository: NEXUS_REPOSITORY,
                    credentialsId: 'nexus-credentials',
                    artifacts: [
                        [artifactId: artifactId, classifier: '', file: artifactPath, type: packaging],
                        [artifactId: artifactId, classifier: '', file: pomFile, type: "pom"]
                    ]
                )
            } else {
                error "*** File could not be found or does not exist at ${artifactPath}."
            }
        }
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
