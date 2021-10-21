def scan(def onFailure) {
  withSonarQubeEnv {
      sh 'env'
    sh(script: 'npm run sonar' )
  }

  withSonarQubeEnv {
    sh 'env'
    script {
        timeout(time: 1, unit: 'HOURS') {
        // Just in case something goes wrong, pipeline will be killed after a timeout
        def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv

        if (qg.status != 'OK') {
          // Retrieve sonar task url and curl to get analysis id
          def SONAR_TASK_PROPERTIES = readProperties file: ".scannerwork/report-task.txt"
          def SONAR_TASK_RESP = sh(returnStdout: true, script: "curl -s -u ${SONAR_AUTH_TOKEN}: ${SONAR_TASK_PROPERTIES.ceTaskUrl} > .scannerwork/sonar-task.json")
          def SONAR_TASK_MAP = readJSON file: '.scannerwork/sonar-task.json'

          // Retrieve quality report
          def QUALITY_GATE_URL = "${SONAR_HOST_URL}/api/qualitygates/project_status?analysisId=${SONAR_TASK_MAP.task.analysisId}"
          def QUALITY_RESP = sh(returnStdout: true, script: "curl -s -u ${SONAR_AUTH_TOKEN}: $QUALITY_GATE_URL > .scannerwork/sonar-quality.json")
          def QUALITY_MAP = readJSON file: '.scannerwork/sonar-quality.json'

          // Filter out conditions that failed
          List failures = QUALITY_MAP['projectStatus']['conditions'].findAll { it['status'] == 'ERROR' }//.collect {

          onFailure("Sonar quality failures:", failures, SONAR_TASK_PROPERTIES.dashboardUrl)
        }
      }
    }
  }
}

def mapSonarFailureToSlackField(def failures) {
  return failures.collect {
    def comparator = it.comparator
    switch (comparator) {
        case "GT":
            comparator = "greater than"
            break
        case "LT":
            comparator = "less than"
            break
    }
    return [title: "Category: ${it.metricKey.replaceAll('_', ' ')}", value: "Threshold: Should not be ${comparator} ${it.errorThreshold}\nActual: ${it.actualValue}", short: false]
  }
}
return this
