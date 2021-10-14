def scan() {
  withSonarQubeEnv("SonarEC2") {
      script {
          def statusCode = sh(script: "SONAR_URL=${SONAR_HOST_URL} SONAR_LOGIN=${SONAR_AUTH_TOKEN} npm run sonarVerify", returnStatus:true)

          if(statusCode != 0) {
              throw new Exception("SonarQube scan failure")
          }
      }
  } 
}

return this