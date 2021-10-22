def loadEnvProps(String env) {
                      def key = "test"
                    def value = "FOOBAR"
                    env."${key}" = "$value"
                    echo env."test"
  // def props = readProperties file: "ci/jenkins/${env}.env"
  // keys = props.keySet()
  // for (key in keys) {
  //   echo "GOT KEY $key"
  //   value = props["${key}"]
  //   echo "GOT VALUE $value"
  //   env."${key}" = "$value"
  // }
}

return this