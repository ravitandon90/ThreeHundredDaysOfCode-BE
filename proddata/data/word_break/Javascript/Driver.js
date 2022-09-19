const fs = require("fs");
const { Solution } = require("./Solution");
const { printFailedCase } = require("../../../javascript/helper");
//  testcases: array of objects
//  {
//      arg1: String,
//      arg2: String,
//      output: boolean
// }
let testcases = [];
main();

function main() {
  let Success = true;
  getTestCases();
  const sizeOfTestCases = testcases.length;

  for (let i = 0; i < sizeOfTestCases; i++) {
    const output_of_user_code = Solution(testcases[i].arg1, testcases[i].arg2);

    if (output_of_user_code !== testcases[i].output) {
      printFailedCase({
        arg1: testcases[i].arg1,
        arg2: testcases[i].arg2,
        arg3: null,
        actual_output: output_of_user_code,
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
      "proddata/data/word_break/testcases.txt",
      "utf8"
    );
    data = data.split("\n");
    for (let i = 0; i < data.length; i++) {
      let inputArr = data[i].trim().split(" ");
      let input1 = inputArr[0];
      inputArr.shift();
      let input2 = inputArr.join(" ");
      i++;

      let output = data[i].trim() === "true" ? true : false;

      testcases.push({
        arg1: input1,
        arg2: input2,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
