const scanner = require("sonarqube-scanner");

scanner(
  {
    // serverUrl: "http://34.217.144.28",
    // token: "3d568c20b05582b00e58ab2b690a30694227ba56",
    options: {
      "sonar.projectName": "LocalJenkinsfile",
      "sonar.projectDescription": 'Description for "My App" project...',
      //"sonar.sources": "dist",
      //"sonar.tests": "specs",
    },
  },
  (thing) => {
    console.log("THING", thing);
    return process.exit();
  }
);
