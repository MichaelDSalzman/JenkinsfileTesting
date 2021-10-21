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
          def SONAR_TASK_RESP = sh(returnStdout: true, script: ". .scannerwork/report-task.txt && curl -s -u ${SONAR_AUTH_TOKEN}: \$ceTaskUrl")
          def SONAR_ANALYSIS_ID = new groovy.json.JsonSlurper().parseText(SONAR_TASK_RESP).task.analysisId

          def SONAR_DASHBOARD_URL = sh(returnStdout: true, script: ". .scannerwork/report-task.txt && echo \$dashboardUrl")

          def QUALITY_GATE_URL = "${SONAR_HOST_URL}/api/qualitygates/project_status?analysisId=$SONAR_ANALYSIS_ID"
          def QUALITY_RESP = sh(returnStdout: true, script: "curl -s -u ${SONAR_AUTH_TOKEN}: $QUALITY_GATE_URL")

          def QUALITY_MAP = new groovy.json.JsonSlurper().parseText(QUALITY_RESP)

          List failures = QUALITY_MAP['projectStatus']['conditions'].findAll { it['status'] == 'ERROR' }//.collect {
            //"${it['metricKey']} is in an error state. Threshold must be ${it.comparator} ${it.errorThreshold}. Actual value is ${it.actualValue}."
          //}
          onFailure("Pipeline aborted due to \<$SONAR_DASHBOARD_URL|sonar quality gate\> failures:", failures)
        }
      }
    }
  }
}

return this
