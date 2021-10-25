// TODO BETTER DOCS
def load(String envName, String envLanguage) {
  def envProps = new Properties()
  envProps.put("GIT_BRANCH", GIT_BRANCH)
  envProps.put("ENV_NAME", envName)
  envProps.put("ENV_LANGUAGE", envLanguage)

  env.getEnvironment().each {
    envProps.put(it.key, it.value)
  }

  def globalProps = readProperties file: "ci/env/global-${envLanguage}.env", interpolate: true, defaults: envProps
  def props = readProperties file: "ci/env/${envName.toLowerCase()}-${envLanguage}.env", interpolate: true, defaults: globalProps
  
  keys = props.keySet()
  for (key in keys) {
    value = props["${key}"]
    env."${key}" = "$value"
  }
}

return this