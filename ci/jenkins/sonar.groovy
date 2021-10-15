def scan(def onFailure) {
  withSonarQubeEnv {
    sh(script: "npm run sonar" )
  } 

    withSonarQubeEnv {
      script {
          timeout(time: 1, unit: 'HOURS') {

         // Just in case something goes wrong, pipeline will be killed after a timeout
            def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv

            if (qg.status != 'OK') {
                onFailure("Pipeline aborted due to sonar quality gate failure: ${qg.status}")
            }
        }
      }
  } 
}

return this