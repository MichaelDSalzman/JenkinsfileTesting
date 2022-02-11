pipeline{
    agent any
  
    stages{
        stage("Where am I?"){
            steps{
                script {
                    currentTag = sh(script: 'git tag --points-at HEAD', returnStdOut: true)
                    def exitCode = sh (returnStatus:true, script:"git branch --all --contains $currentTag | grep \"/main\$\"")
                    echo exitCode
            }
        }
        
    }
}