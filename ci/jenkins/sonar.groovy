def scan(def onFailure) {
  withSonarQubeEnv("SonarEC2") {
      script {
          // def statusCode = sh(script: "SONAR_URL=${SONAR_HOST_URL} SONAR_LOGIN=${SONAR_AUTH_TOKEN} npm run sonarVerify", returnStatus:true )
        //   def statusCode = sh(script: "npm run sonar", returnStatus:true )
          def statusCode = sh(script: "SONAR_PROJECT_KEY=AppFramework SONAR_URL=${SONAR_HOST_URL} SONAR_LOGIN=${SONAR_AUTH_TOKEN} npm run sonarMike", returnStatus:true )
          if(statusCode != 0) {
              onFailure("SonarQube failed. See http://${SONAR_HOST_URL} for more details")
          }
      }
  } 
}

return this