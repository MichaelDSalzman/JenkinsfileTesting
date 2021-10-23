// TODO BETTER DOCS
def load() {
  def globalProps = readProperties file: "ci/env/global-${env.envLanguage}.env", interpolate: true//, defaults: env
  def props = readProperties file: "ci/env/${env.envName}-${env.envLanguage}.env", interpolate: true, defaults: globalProps
  keys = props.keySet()
  for (key in keys) {
    value = props["${key}"]
    env."${key}" = "$value"
  }
}

return this