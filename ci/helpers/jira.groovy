String findJiraIssueIdFromBranch() {
def jiraIssueIdMatcher = env.GIT_BRANCH =~ /^.*?(AF-\d+)-.*$/
  if (jiraIssueIdMatcher.matches()) {
      return jiraIssueIdMatcher[0][1]
  }

  null
}

return this