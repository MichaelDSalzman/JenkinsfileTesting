// TODO BETTER DOCS
enum Comparators {
    GT("sonar_failure_comparator_gt"),
    LT("sonar_failure_comparator_lt")

    String description;

    public Comparators(String descriptionConfigKey) {
        this.description = env."${descriptionConfigKey}";
    }

    public String getDescription() {
        return description;
    }
}

def buildDetailedSonarFailureSlackMessage(List failedConditions, String sonarDashboardUrl) {
  def fields = failedConditions.collect {
    return [
      title: "${env.sonar_failure_slack_failure_type_header}: ${it.metricKey.replaceAll('_', ' ')}", 
      value: "${env.sonar_failure_slack_failure_type_body_threshold} ${Comparators.valueOf(it.comparator).getDescription()} ${it.errorThreshold}\n${env.sonar_failure_slack_failure_type_body_actual}: ${it.actualValue}", 
      short: false
    ]
  }
  return [
      color: "danger",
      title: "${env.sonar_failure_slack_title}",
      titleLink: "$BUILD_URL",
      text: env.sonar_failure_slack_subtitle,
      fields: [[title: env.sonar_failure_slack_report_url_header, value: sonarDashboardUrl]] + fields
  ]
}

def buildJiraSuccessComment(String message = '') {
  return buildJiraComment("Build {color:green}*completed*{color}\n\n ${message}")
}

def buildJiraFailureComment(String message = '') {
  return buildJiraComment("Build {color:red}*failed*{color}\n\n ${message}")
}

def buildJiraComment(String message) {
  return "${message} {quote}*Branch*: ${GIT_BRANCH} \n\n *Changeset:* ${GIT_COMMIT} \n\n*Environment:* ${env.ENV_NAME}{quote}"
}

def buildDetailedSonarFailureJiraMessage(List failedConditions, String sonarDashboardUrl) {
  def fields = failedConditions.collect {
    return "*${it.metricKey.replaceAll('_', ' ').capitalize()}*: ${env.sonar_failure_jira_failure_threshold} ${Comparators.valueOf(it.comparator).getDescription()} *${it.errorThreshold}*. ${env.sonar_failure_jira_failure_type_body_actual}: *${it.actualValue}*"
  }
  return buildJiraFailureComment("${env.sonar_failure_jira_title} ${sonarDashboardUrl} {quote}${fields.join('\n\n')}{quote}")
}

return this