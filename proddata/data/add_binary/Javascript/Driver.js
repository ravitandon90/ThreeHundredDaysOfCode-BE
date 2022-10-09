const fs = require("fs");
const { Solution } = require("./Solution");
const { printFailedCase } = require("../../../Javascript/helper");

let testcases = [];
//  testcases: array of objects
//  {
//      arg1: String,
//      arg2: String,
//      output: String
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
      "proddata/data/add_binary/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 0; i < data.length; i++) {
      data[i] = data[i].trim();
      let InputArr = data[i].split(" ");
      let input1 = InputArr[0],
        input2 = InputArr[1];

      i++;

      data[i] = data[i].trim();
      let output = data[i];

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
