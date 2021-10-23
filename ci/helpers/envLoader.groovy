// TODO BETTER DOCS
def load() {
  def envProps = new Properties()
  envProps.put("GIT_BRANCH", GIT_BRANCH)

  echo envProps.toString()
  env.getEnvironment().each {
    envProps.put(it.key, it.value)
  }
  echo envProps.toString()
  def globalProps = readProperties file: "ci/env/global-${env.ENV_LANGUAGE}.env", interpolate: true, defaults: envProps
  def props = readProperties file: "ci/env/${env.ENV_NAME.toLower()}-${env.ENV_LANGUAGE}.env", interpolate: true, defaults: globalProps
  keys = props.keySet()
  for (key in keys) {
    value = props["${key}"]
    env."${key}" = "$value"
  }
}

return this