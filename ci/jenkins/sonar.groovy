def scan(def failureCallback) {
  withSonarQubeEnv("SonarEC2") {
      script {
          def statusCode = sh(script: "SONAR_URL=${SONAR_HOST_URL} SONAR_LOGIN=${SONAR_AUTH_TOKEN} npm run sonarVerify", returnStatus:true)

          if(statusCode != 0) {
              failureCallback("SonarQube failed")
          }
      }
  } 
}

return this