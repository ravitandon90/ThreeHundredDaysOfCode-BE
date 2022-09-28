const fs = require("fs");
const { Solution } = require("./Solution");
const { printFailedCase } = require("../../../Javascript/helper");

// Solution function takes 2 arguments, 1st is the array of Numbers and 2nd one is a Number, return type of the function is a Number
let testcases = [];
//  testcases: array of objects
//  {
//      arg1: String,
//      arg2: String,
//      output: Number
// }

main();

function main() {
  getTestCases();
  let Success = true;
  const sizeOfTestCases = testcases.length;

  for (let i = 0; i < sizeOfTestCases; i++) {
    const user_code_output = Solution(testcases[i].arg1, testcases[i].arg2);
    if (user_code_output !== testcases[i].output) {
      printFailedCase({
        arg1: testcases[i].arg1,
        arg2: testcases[i].arg2,
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
      "proddata/data/jewels_and_stones/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 0; i < data.length; i++) {
      let input1 = data[i].trim().split(" ");
      i++;

      let output = Number(data[i].trim());

      testcases.push({
        arg1: input1[0],
        arg2: input1[input1.length - 1],
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
