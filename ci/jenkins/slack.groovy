def sendSuccessMessage(String message) {
  slackSend(color:"good", message: "<$BUILD_URL|Build> - $message")
}

def sendFailureMessage(String message) {
  slackSend(color:"danger", message: "<$BUILD_URL|Build> - $message")
}

def sendDetailedMessage(Map parameters) {
  def attachments = [
      [
        mrkdwn_in: ["title", "text"],
        color: parameters.color,
        title: parameters.title,
        title_link: parameters.titleLink,
        text: parameters.text,
        fields: parameters.fields
      ]
  ]

  slackSend(channel: "#general", attachments: attachments)
}

return this