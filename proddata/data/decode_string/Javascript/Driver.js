const fs = require("fs");
const { Solution } = require("./Solution");
const {
  compare1DArray,
  convertStringArrToNumArr,
  getArrFromStr,
  printFailedCase,
} = require("../../../Javascript/helper");

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
      "proddata/data/decode_string/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let input1 = [];

      input1 = data[i].trim();
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
