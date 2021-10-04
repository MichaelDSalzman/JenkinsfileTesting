pipeline{
    agent any
  
    stages{
        stage("Install dependencies"){
            steps{
                echo "========Installing dependencies======== ${params}"
            }
            post{
                always{
                    echo "========always========"
                }
                success{
                    echo "========A executed successfully========"
                }
                failure{
                    echo "========A execution failed========"
                }
            }
        }
        stage("NexusIQ Scan"){
            steps{
                echo "========executing NexusIQ scan======== ${params}"
            }
        }
        stage("Unit test"){
            steps{
                echo "========executing unit tests======== ${params}"
            }
        }
        stage("SonarQube scan"){
            steps{
                echo "========executing SonarQube scan======== ${params}"
            }
        }
        stage("Package"){
            steps{
                echo "========Packaging======== ${params}"
            }
        }
    }
    post{
        always{
            echo "========always========"
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}