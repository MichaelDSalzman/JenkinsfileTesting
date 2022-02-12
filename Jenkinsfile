pipeline{
    agent any
  
    stages{
        stage("Where am I?"){
            steps{
                script {
                    currentTag = sh(script: 'git tag --points-at HEAD', returnStdout: true)
                    echo currentTag
                }
            }
        }
    }
}