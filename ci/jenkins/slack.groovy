def sendSuccessMessage(String message) {
  echo "SUCCESS - Called from slack"
  slackSend(color:"good", message: "<$BUILD_URL|Build> $message")
}

def sendFailureMessage(String message) {
  echo "FAILURE - Called from slack"
  slackSend(color:"danger", message: "<$BUILD_URL|Build> $message")
}

return this