def scan(def onFailure) {
  withSonarQubeEnv("SonarEC2") {
      script {
          def statusCode = sh(script: "SONAR_URL=${SONAR_HOST_URL} SONAR_LOGIN=${SONAR_AUTH_TOKEN} npm run sonarVerify", returnStatus:true | tee sonar.out)
          if(statusCode != 0) {

              onFailure("SonarQube failed. See http://${SONAR_HOST_URL}/dashboard?id=TODO for more details")
          }
      }
  } 
}

return this