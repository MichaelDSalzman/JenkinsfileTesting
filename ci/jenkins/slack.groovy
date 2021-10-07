def sendSuccessMessage(String message) {
  echo "SUCCESS - Called from slack"
  slackSend(color:"good", message: message)
}

def sendFailureMessage(String message) {
  echo "FAILURE - Called from slack"
  slackSend(color:"danger", message: message)
}

return this