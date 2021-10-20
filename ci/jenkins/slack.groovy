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
            color: "#36a64f",
            pretext: "Optional pre-text that appears above the attachment block",
            author_name: "author_name",
            author_link: "http://flickr.com/bobby/",
            author_icon: "https://placeimg.com/16/16/people",
            title: "title",
            title_link: "https://api.slack.com/",
            text: "Optional `text` that appears within the attachment",
            fields: [
                {
                    title: "A field's title",
                    value: "This field's value",
                    short: false
                },
                {
                    title: "A short field's title",
                    value: "A short field's value",
                    short: true
                },
                {
                    title: "A second short field's title",
                    value: "A second short field's value",
                    short: true
                }
            ],
            thumb_url: "http://placekitten.com/g/200/200",
            footer: "footer",
            footer_icon: "https://platform.slack-edge.com/img/default_application_icon.png",
            ts: 123456789
        
    ]
]

slackSend(channel: "#general", attachments: attachments)

}

return this