
someEnums = load("ci/jenkins/someEnums.groovy")

env.BUILD_STEPS_TO_INCLUDE=[someEnums.BuildPipelineSteps.INSTALL_DEPS]