pipeline {
	agent any
	tools {
		maven 'maven'
		jdk 'Java JDK 17'
	}
	stages{
        stage("clean"){
            steps{
                echo "Start Clean"
                bat "mvn clean"
            }
        }
        stage("test"){
            steps{
                echo "Start Test"
                bat "mvn test"
            }
        }
        stage("build"){
            steps{
                echo "Start build"
                bat "mvn install -DskipTests"
            }
        }
        stage("scan"){
        	steps {
        		echo "Start scan"
        		bat "mvn sonar:sonar"
        	}
        }
    }
}