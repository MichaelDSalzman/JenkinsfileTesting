String findJiraIssueIdFromBranch() {
def jiraIssueIdMatcher = env.GIT_BRANCH =~ env.jira_issue_id_git_branch_pattern
  if (jiraIssueIdMatcher.matches()) {
      return jiraIssueIdMatcher[0][1]
  }

  null
}

void addComment(String jiraIssueId, String comment) {
  script {
      withEnv(['JIRA_SITE=EC2']) {
          def input = [body: comment]
          jiraAddComment idOrKey: jiraIssueId, input: input
      }
  }
}

return this