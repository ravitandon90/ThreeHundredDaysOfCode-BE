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
    let arg1 = testcases[i].arg1;
    const user_code_output = Solution(arg1);

    if (user_code_output !== testcases[i].output) {
      printFailedCase({
        arg1: arg1,
        arg2: null,
        arg3: null,
        arg4: null,
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
      "proddata/data/number_of_1_bits/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 0; i < data.length; i++) {
      let input1 = data[i].trim();

      // jumps to output of that testcase
      i++;

      let output = data[i].trim();

      testcases.push({
        arg1: input1,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
