String findJiraIssueIdFromBranch() {
def jiraIssueIdMatcher = env.GIT_BRANCH =~ env.jira_issue_id_git_branch_pattern
  if (jiraIssueIdMatcher.matches()) {
      return jiraIssueIdMatcher[0][1]
  }

  null
}

return this