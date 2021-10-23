def scan(def onFailure) {
  // withSonarQubeEnv {
  //   sh(script: 'npm run sonar' )
  // }

  // withSonarQubeEnv {
  //   script {
  //     timeout(time: env.sonar_timeoutInMinutes, unit: 'MINUTES') {
  //       // Just in case something goes wrong, pipeline will be killed after a timeout
  //       def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv

  //       if (qg.status != 'OK') {
  //         // Retrieve sonar task url and curl to get analysis id
  //         def SONAR_TASK_PROPERTIES = readProperties file: ".scannerwork/report-task.txt"
  //         def SONAR_TASK_RESP = sh(returnStdout: true, script: "curl -s -u ${SONAR_AUTH_TOKEN}: ${SONAR_TASK_PROPERTIES.ceTaskUrl} > .scannerwork/sonar-task.json")
  //         def SONAR_TASK_MAP = readJSON file: '.scannerwork/sonar-task.json'

  //         // Retrieve quality report
  //         def QUALITY_GATE_URL = "${SONAR_HOST_URL}/api/qualitygates/project_status?analysisId=${SONAR_TASK_MAP.task.analysisId}"
  //         def QUALITY_RESP = sh(returnStdout: true, script: "curl -s -u ${SONAR_AUTH_TOKEN}: $QUALITY_GATE_URL > .scannerwork/sonar-quality.json")
  //         def QUALITY_MAP = readJSON file: '.scannerwork/sonar-quality.json'

  //         // Filter out conditions that failed
  //         List failedConditions = QUALITY_MAP['projectStatus']['conditions'].findAll { it['status'] == 'ERROR' }

  //         onFailure([failedConditions: failedConditions, dashboardUrl: SONAR_TASK_PROPERTIES.dashboardUrl])
  //       }
  //     }
  //   }
  // }

  onFailure([failedConditions: [
    [metricKey: "code_coverage", comparator: "LT", errorThreshold: 80, actualValue: 1],
    [metricKey: "code_smells", comparator: "GT", errorThreshold: 5, actualValue: 60],
    ], 
    dashboardUrl: "http://google.com"])
}

return this