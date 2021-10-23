// TODO BETTER DOCS
def load() {
  def envProps = new Properties().putAll(env.getEnvironment())
  echo env.getEnvironment().toString()
  env.getEnvironment().each {
    envProps.put(it.key, it.value)
  }
  echo envProps.toString()
  def globalProps = readProperties file: "ci/env/global-${env.envLanguage}.env", interpolate: true, defaults: envProps
  def props = readProperties file: "ci/env/${env.envName}-${env.envLanguage}.env", interpolate: true, defaults: globalProps
  keys = props.keySet()
  for (key in keys) {
    value = props["${key}"]
    env."${key}" = "$value"
  }
}

return this