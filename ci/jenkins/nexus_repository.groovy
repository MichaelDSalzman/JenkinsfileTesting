enum MavenRepoType {
  SNAPSHOT,
  RELEASE
}

class MavenRepo {
  private String name
  // private MavenRepoType type

  // public MavenRepo(String name, MavenRepoType type) {
  public MavenRepo(String name) {
    this.name = name
    // this.type = type
  }

  public String getName() {
    return name
  }

  // public MavenRepoType getType() {
  //   return type
  // }
}

// class MavenArtifact {
//   private MavenRepo repo
//   private String groupId
//   private String artifactId
//   private String artifactVersion

//   public MavenArtifact(MavenRepo repo, String groupId, String artifactId, String artifactVersion) {
//     this.repo = repo
//     this.groupId = groupId
//     this.artifactId = artifactId
//     this.artifactVersion = artifactVersion
//   }

//   public MavenRepo getRepo() {
//     return repo
//   }

//   public String getGroupId() {
//     return groupId
//   }
//   public String getArtifactId() {
//     return artifactId
//   }
//   public String getArtifactVersion() {
//     return artifactVersion
//   }
// }

// MavenRepo constructMavenRepo(String name, boolean isSnapshot) {
//   return new MavenRepo(name, isSnapshot ? MavenRepoType.SNAPSHOT: MavenRepoType.RELEASE)
// }

MavenRepo constructMavenRepo(String name) {
  return new MavenRepo(name)
}

// MavenArtifact constructMavenArtifact(MavenRepo repo, String groupId, String artifactId, String artifactVersion) {
//   return new MavenArtifact(repo, groupId, artifactId, artifactVersion)
// }

// String artifactVersionRename(String artifactVersion, MavenRepoType repoType) {
//   return MavenRepoType.SNAPSHOT == repoType ? "$artifactVersion-SNAPSHOT" : artifactVersion
// }

// void packageAndUploadZip(String sourceFolder, MavenArtifact artifact) {
//   def fileName = "${artifact.getArtifactId()}-${artifact.getArtifactVersion()}.zip"
//   zip zipFile: fileName, archive: true, dir: sourceFolder
//   uploadArtifact(fileName, artifact)
// }

// void uploadArtifact(String sourceFile, MavenArtifact artifact) {
//   artifactVersion = artifactVersionRename(artifact.getArtifactVersion(), artifact.getRepo().getType())

//   container('manage-jdk8-npm') {
//     configFileProvider([configFile(fileId: 'maven-settings', variable: 'MAVEN_SETTINGS_XML')]) {

//       sh """
//           echo "------------upload artifacts-----------------"
//           mvn deploy:deploy-file \
//           -DgroupId=${artifact.getGroup()} \
//           -DartifactId=${artifact.getArtifactId()}  \
//           -DgeneratePom=true \
//           -Dpackaging=zip \
//           -DrepositoryId=${artifact.getRepo().getName()} \
//           -Dversion=${artifact.getArtifactVersion()} \
//           -DuniqueVersion=false \
//           -Durl=https://nexus.aws.enlightedinc.com/repository/${artifact.getRepo().getName()}/ \
//           -Dfile=${sourceFile} \
//           -DskipTests \
//           -s $MAVEN_SETTINGS_XML
//           """
//     }
//   }
// }

// void downloadArtifact(MavenArtifact artifact, String destination) {

//   echo "${artifact.getGroupId()} - ${artifact.getArtifactId()} - ${artifact.getArtifactVersion()}"
//   echo destination
//   // artifactVersion = artifactVersionRename(artifact.getArtifactVersion(), artifact.getRepo().getType())

//   // container('manage-jdk8-npm') {
//   //   configFileProvider([configFile(fileId: 'maven-settings', variable: 'MAVEN_SETTINGS_XML')]) {

//   //     String fileName = "${artifact.getArtifactId()}-${artifact.getArtifactVersion()}.zip"

//   //     sh """
//   //       mkdir -p ${WORKSPACE}/download
//   //       mvn dependency:get \
//   //       -DrepositoryId=${artifact.getRepo().getName()} \
//   //       -Dartifact=${artifact.getGroupId()}:${artifact.getArtifactId()}:${artifact.getArtifactVersion()}:zip \
//   //       -Dtransitive=false \
//   //       -Ddest=${destination} \
//   //       -DremoteRepositories=https://nexus.aws.enlightedinc.com/repository/${artifact.getRepo().getName()}/ \
//   //       -s $MAVEN_SETTINGS_XML
//   //     """
//   //   }
//   // }
// }

// void deleteRemoteArtifact(MavenArtifact artifact) {
//   withCredentials([usernamePassword(credentialsId: 'jenkins-nexus', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASSWORD')]) {
//     def nexusComponentId = sh(returnStdout: true, script: "curl -k -u $NEXUS_USER:$NEXUS_PASSWORD 'https://nexus.aws.enlightedinc.com/service/rest/v1/search?repository=${artifact.getRepo().getName()}&group=${artifact.getGroupId()}&name=${artifact.getArtifactId()}&version=${artifact.getArtifactVersion()}*' | jq -r .items[0].id").trim()
//     sh(script: "curl -k -u $NEXUS_USER:$NEXUS_PASSWORD -X DELETE 'https://nexus.aws.enlightedinc.com/service/rest/v1/components/${nexusComponentId}'")
//   }
// }

// void promoteArtifact(MavenArtifact snapshotArtifact, MavenArtifact releaseArtifact) {
//   def downloadLocation = "$WORKSPACE/${snapshotArtifact.getArtifactId()}-${snapshotArtifact.getArtifactVersion()}.zip"
//   downloadArtifact(snapshotArtifact, downloadLocation)
//   uploadArtifact(downloadLocation, releaseArtifact)
//   deleteRemoteArtifact(snapshotArtifact)
// }

return this