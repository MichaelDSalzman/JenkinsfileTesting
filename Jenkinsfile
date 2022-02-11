pipeline{
    agent any
  
    stages{
        stage("Where am I?"){
            steps{
                script {
                    currentTag = sh(script: 'git tag --points-at HEAD', returnStdOut: true)
                    echo currentTag
                    def exitCode = sh (returnStatus:true, script:"git branch --all --contains $currentTag | grep \"/main\$\"")
                    echo exitCode.toString()
                }
            }
        }
    }
}