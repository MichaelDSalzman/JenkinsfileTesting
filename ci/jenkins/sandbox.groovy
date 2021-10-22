env.sonar_failure_slack_color="danger"
env.sonar_failure_slack_title="Sandbox build failed on branch $GIT_BRANCH"
env.sonar_failure_slack_title_link="$BUILD_URL"
env.sonar_timeoutInMinutes=10

env.sonar = [
    timeoutInMinutes: 60,
    failure: [
        slack: [
            color: "danger",
            title: "Sandbox build failed on branch $GIT_BRANCH",
            title_link: "$BUILD_URL"
        ]
    ]
]