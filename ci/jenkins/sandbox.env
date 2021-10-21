env.sonar_failure_slack_color="danger"
env.sonar_failure_slack_title="Sandbox build failed on branch $GIT_BRANCH"
env.sonar_failure_slack_title_link="$BUILD_URL"
env.sonar_timeoutInMinutes=10

env.sonar_failure_fn={String message, List failures, String sonarDashboardUrl ->
                        slackLib.sendDetailedMessage(
                            [
                                color: env.sonar_failure_slack_color, 
                                title: env.sonar_failure_slack_title,
                                title_link: env.sonar_failure_slack_title_link,
                                text: message,
                                fields: [[title: "Report URL", value: sonarDashboardUrl]] + sonarLib.mapSonarFailuresToSlackFields(failures)
                            ]
                        )
                        error message
                    }