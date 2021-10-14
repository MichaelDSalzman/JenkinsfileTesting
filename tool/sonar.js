const verifier = require("sonarqube-verify/lib/sonarqube-verify");

verifier
  .verify({})
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
