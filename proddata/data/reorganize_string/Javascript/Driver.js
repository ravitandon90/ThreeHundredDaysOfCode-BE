const fs = require("fs");
const { Solution } = require("./Solution");
const { printFailedCase } = require("../../../Javascript/helper");

let testcases = [];
//  testcases: array of objects
//  {
//      arg1: String,
//      output: String
// }

main();

function main() {
  getTestCases();

  let Success = true;
  const sizeOfTestCases = testcases.length;

  for (let i = 0; i < sizeOfTestCases; i++) {
    const user_code_output = Solution(testcases[i].arg1);

    if (user_code_output !== testcases[i].output) {
      printFailedCase({
        arg1: testcases[i].arg1,
        arg2: null,
        arg3: null,
        actual_output: user_code_output,
        expected_output: testcases[i].output,
      });

      Success = false;
      break;
    }
  }

  if (Success) {
    console.log("Result: Success");
  }
}

function getTestCases() {
  try {
    let data = fs.readFileSync(
      "proddata/data/reorganize_string/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let input1 = "";
      let j = 1;
      data[i] = data[i].trim();

      while (j < data[i].length - 1) {
        input1 += data[i][j];
      }

      i++;

      data[i] = data[i].trim();
      let output = "";
      j = 1;

      while (j < data[i].length - 1) {
        output += data[i][j];
      }

      testcases.push({
        arg1: input1,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
