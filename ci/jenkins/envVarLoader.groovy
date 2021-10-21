def loadEnvProps(String env) {
  def props = readProperties file: "ci/jenkins/${env}.env"
  keys = props.keySet()
  for (key in keys) {
    value = props["${key}"]
    env."${key} = "${value}"
  }
}

return this