enum BuildPipelineSteps {
  INSTALL_DEPS,
  UNIT_TEST,
  UNIT_TEST_COVERAGE,
  SONAR_SCAN,
  NEXUS_SCAN,

  public BuildPipelineSteps() {}
}

this.BuildPipelineSteps = BuildPipelineSteps

return this