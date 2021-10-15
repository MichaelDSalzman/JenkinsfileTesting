def sendSuccessMessage(String message) {
  echo "SUCCESS - Called from slack"
  slackSend(color:"good", message: "[Build]($BUILD_URL) $message")
}

def sendFailureMessage(String message) {
  echo "FAILURE - Called from slack"
  slackSend(color:"danger", message: "[Build]($BUILD_URL) $message")
}

return this