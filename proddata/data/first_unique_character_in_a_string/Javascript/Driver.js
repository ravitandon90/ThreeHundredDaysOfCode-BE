const fs = require("fs");
const { Solution } = require("./Solution");
const { printFailedCase } = require("../../../Javascript/helper");

// Solution function takes 2 arguments, 1st is the array of Numbers and 2nd one is a Number, return type of the function is a Number
let testcases = [];
//  testcases: array of objects
//  {
//      arg1: [],
//      output: boolean
// }

main();

function main() {
  getTestCases();
  console.log(testcases);
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
      "proddata/data/first_unique_character_in_a_string/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let input1 = [];
      let j = 1,
        input1Str = "";

      while (j < data[i].length - 1) {
        input1Str += data[i][j];
        j++;
      }

      i++;

      input1 = input1Str.trim();
      let output = Number(data[i].trim());

      testcases.push({
        arg1: input1,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
