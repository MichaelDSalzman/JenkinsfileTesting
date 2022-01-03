def someEnums2 = load("ci/jenkins/someEnums.groovy")

env.BUILD_STEPS_TO_INCLUDE=[someEnums2.BuildPipelineSteps.INSTALL_DEPS]