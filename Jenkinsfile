pipeline{
    agent any
  
    stages{
        stage("Where am I?"){
            steps{
                script {
                    def defaultHeader = load('./defaultHeader.groovy')
                    defaultHeader.setDefaultHeader("HELLO WORLD!")
                    defaultHeader.printDefaultHeader()
                }
            }
        }
    }
}