def scan(def onFailure) {
  withSonarQubeEnv("SonarEC2") {
      script {
          timeout(time: 1, unit: 'HOURS') {
          // def statusCode = sh(script: "SONAR_URL=${SONAR_HOST_URL} SONAR_LOGIN=${SONAR_AUTH_TOKEN} npm run sonarVerify", returnStatus:true )
          sh(script: "npm run sonar" )
        //   sh(script: "SONAR_PROJECT_KEY=AppFramework SONAR_URL=${SONAR_HOST_URL} SONAR_LOGIN=${SONAR_AUTH_TOKEN} npm run sonarMike" )
        //   if(statusCode != 0) {
        //       onFailure("SonarQube failed. See http://${SONAR_HOST_URL} for more details")
        //   }

         // Just in case something goes wrong, pipeline will be killed after a timeout
            def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
            if (qg.status != 'OK') {
                error "Pipeline aborted due to quality gate failure: ${qg.status}"
            }
        }
      }
  } 
}

return this