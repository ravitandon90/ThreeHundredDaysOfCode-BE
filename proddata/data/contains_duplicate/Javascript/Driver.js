const fs = require("fs");
const { Solution } = require("./Solution");
const {
  compare1DArray,
  convertStringArrToNumArr,
  getArrFromStr,
  printFailedCase,
} = require("../../../Javascript/helper");

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
      "proddata/data/contains_duplicate/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let input1 = [];
      let j = 0,
        input1Str = "";

      while (j < data[i].length && data[i][j] != "]") {
        input1Str += data[i][j];
        j++;
      }
      j++;

      input1Str += "]";

      i++;

      input1 = getArrFromStr(input1Str);
      input1 = convertStringArrToNumArr(input1);
      let output = data[i].trim() === "true" ? true : false;

      testcases.push({
        arg1: input1,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
