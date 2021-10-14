const verifier = require("sonarqube-verify/lib/sonarqube-verify");

const projectKey =
  process.env.SONAR_PROJECT_KEY || pkg.name.replace("@", "").replace("/", ":");

// sonar parameters
const sonarUrl = process.env.SONAR_URL || "http://localhost:9000";
const sonarLogin = process.env.SONAR_LOGIN || "";
const sonarPassword = process.env.SONAR_PASSWORD || "";
const sonarSkip = process.env.SONAR_SKIP || false;
const gateSkip = process.env.SONAR_GATE_SKIP || false;

const params = {
  "sonar.projectKey": projectKey,
  "sonar.projectVersion": pkg.version,
  "sonar.projectName": pkg.name,
  "sonar.host.url": sonarUrl,
  "sonar.login": sonarLogin,
  "sonar.skip": sonarSkip,
  "sonar.gate.skip": gateSkip,
  "sonar.password": sonarPassword,
};

verifier
  .verify(params)
  .then((result) => {
    console.log("Verification ended in success");
    console.log(result);
    process.exit(0);
  })
  .catch((err) => {
    console.log("Verification ended in error");
    console.error(err);
    if (err.stack) {
      debug(err.stack);
    }
    process.exit(1);
  });
