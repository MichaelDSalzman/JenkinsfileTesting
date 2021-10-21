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
          def SONAR_TASK_RESP = sh(returnStdout: true, script: ". .scannerwork/report-task.txt && curl -s -u ${SONAR_AUTH_TOKEN}: \$ceTaskUrl")
          def SONAR_ANALYSIS_ID = new groovy.json.JsonSlurperClassic().parseText(SONAR_TASK_RESP).task.analysisId

          // Parse sonar properties file to get dashboard URL
          def SONAR_DASHBOARD_URL = sh(returnStdout: true, script: ". .scannerwork/report-task.txt && echo \$dashboardUrl")

          // Retrieve quality report
          def QUALITY_GATE_URL = "${SONAR_HOST_URL}/api/qualitygates/project_status?analysisId=$SONAR_ANALYSIS_ID"
          def QUALITY_RESP = sh(returnStdout: true, script: "curl -s -u ${SONAR_AUTH_TOKEN}: $QUALITY_GATE_URL")
          def QUALITY_MAP = new groovy.json.JsonSlurperClassic().parseText(QUALITY_RESP)

          // Filter out conditions that failed
          List failures = QUALITY_MAP['projectStatus']['conditions'].findAll { it['status'] == 'ERROR' }//.collect {

          onFailure("Sonar quality failures:", failures, SONAR_DASHBOARD_URL)
        }
      }
    }
  }
}

return this
