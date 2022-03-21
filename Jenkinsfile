pipeline{
    agent any
  
    stages{
        stage("Where am I?"){
            steps{
                script {
                    def defaultHeader = load('./defaultHeader.groovy')
                    defaultHeader.setDefaultHeader("HELLO WORLD!")
                    defaultHeader.printDefaultHeader()
                    defaultHeader.printDefaultHeader()
                    defaultHeader.printDefaultHeader()
                    defaultHeader.printDefaultHeader()
                    defaultHeader.printDefaultHeader()


                    defaultHeader.setDefaultHeader("Goodbye WORLD!")
                    defaultHeader.printDefaultHeader()
                    defaultHeader.printDefaultHeader()
                    defaultHeader.printDefaultHeader()
                    defaultHeader.printDefaultHeader()
                    defaultHeader.printDefaultHeader()
                }
            }
        }
    }
}