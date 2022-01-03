def loadProps(def enums) {
  env.BUILD_STEPS_TO_INCLUDE=[enums.BuildPipelineSteps.INSTALL_DEPS,enums.BuildPipelineSteps.SONAR_SCAN,enums.BuildPipelineSteps.NEXUS_SCAN]
}

return this