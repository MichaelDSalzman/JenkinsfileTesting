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

  def attachments = [
[
	        mrkdwn_in: ["text"],
            color: "danger",
            //pretext: "Optional pre-text that appears above the attachment block",
            author_name: "author_name",
            title: "$GIT_BRANCH",
            title_link: "$BUILD_URL",
            text: "Build failed.",
            fields: [
                [
                    title: "Step",
                    value: "Step that failed",
                    short: false
                ],
                [
                    title: "Reason",
                    value: "Reason it failed",
                    short: false
                ]
            ]
    ]
]

slackSend(channel: "#general", attachments: attachments)

}

return this