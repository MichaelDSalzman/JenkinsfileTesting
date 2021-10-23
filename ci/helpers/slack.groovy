def success(String message) {
    sendMessage("good", message)
}

def failure(String message) {
    sendMessage("danger", message)
}

def warning(String message) {
    sendMessage("warning", message)
}

def sendMessage(String color, String message) {
    sh "env"
    slackSend(channel: env.slack_default_channel, color:"${color}", message: "<$BUILD_URL|${env.slack_message_build_link_text}> - $GIT_BRANCH - $message")
}

// TODO BETTER DOCS
def sendDetailedMessage(Map params) {
  def attachments = [
      [
        mrkdwn_in: ["title", "text"],
        color: params.color,
        title: params.title,
        title_link: params.titleLink,
        text: params.text,
        fields: params.fields
      ]
  ]

  slackSend(channel: env.slack_default_channel, attachments: attachments)
}

return this