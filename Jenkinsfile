pipeline{
    agent any

    parameters {
        string(name: "TESTSTRING", description: "Some description")
    }
  
    stages{
        stage("A"){
            steps{
                echo "========executing A======== ${params.TESTSTRING}"
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