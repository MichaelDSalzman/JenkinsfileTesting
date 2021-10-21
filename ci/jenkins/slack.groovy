def sendSuccessMessage(String message) {
  slackSend(color:"good", message: "<$BUILD_URL|Build> - $message")

  def attachments = [
  [
    text: 'I find your lack of faith disturbing!',
    fallback: 'Hey, Vader seems to be mad at you.',
    color: '#ff0000'
  ]
]

slackSend(channel: "#general", attachments: attachments)

}

def sendFailureMessage(String message) {
  slackSend(color:"danger", message: "<$BUILD_URL|Build> - $message")
}

def sendDetailedMessage(String color, String title, String titleLink, String message, List fields) {
  def attachments = [
      [
        mrkdwn_in: ["text"],
        color: color,
        title: title,
        title_link: titleLink,
        text: message,
        fields: fields
      ]
  ]

  slackSend(channel: "#general", attachments: attachments)
}

return this