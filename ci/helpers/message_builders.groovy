def generateSonarFailureDetailedSlackMessage(List failedConditions, String sonarDashboardUrl) {
  def fields = failedConditions.collect {
    def comparator = it.comparator
    switch (comparator) {
        case "GT":
            comparator = env."sonar_failure_slack_comparator_gt"
            break
        case "LT":
            comparator = env."sonar_failure_slack_comparator_lt"
            break
    }
    return [
      title: "${env."sonar_failure_slack_failure_type_header"}: ${it.metricKey.replaceAll('_', ' ')}", 
      value: "${env."sonar_failure_slack_failure_type_body_threshold"} ${comparator} ${it.errorThreshold}\n${env."sonar_failure_slack_failure_type_body_actual"}: ${it.actualValue}", 
      short: false
    ]
  }
  return [
      color: "danger",
      title: env."sonar_failure_slack_title",
      titleLink: "$BUILD_URL",
      text: env."sonar_failure_slack_subtitle",
      fields: [[title: env."sonar_failure_slack_report_url_header", value: sonarDashboardUrl]] + failures
  ]
}

return this