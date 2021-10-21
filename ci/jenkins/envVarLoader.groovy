def loadEnvProps(String env) {
  def props = readProperties file: "ci/jenkins/${env}.env"
  keys = props.keySet()
  for (key in keys) {
    echo "GOT KEY $key"
    value = props["${key}"]
    echo "GOT VALUE $key"
    env."${key}" = value
  }
}

return this