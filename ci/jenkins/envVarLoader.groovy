@NonCPS
def loadEnvProps(String env) {
  def props = readProperties file: "ci/jenkins/${env}.env", interpolate: true
  keys = props.keySet()
  for (key in keys) {
    value = props["${key}"]
    echo key
    echo value
    env."${key}" = "${->value}"
    echo env.key
  }
}

return this