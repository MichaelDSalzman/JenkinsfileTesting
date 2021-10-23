def slackChannel = '#general'

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
    slackSend(channel: slackChannel, color:"${color}", message: "<$BUILD_URL|${env."slack_message_build_link_text"}> - $BRANCH_NAME - $message")
}

// Send a more detailed message. Color is either a hex code or one of "good", "danger", "warning".
//  Field object is a map with properties {title: String, value: String}
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

  slackSend(channel: slackChannel, attachments: attachments)
}

return this