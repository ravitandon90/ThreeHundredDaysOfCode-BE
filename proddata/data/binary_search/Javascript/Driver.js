const fs = require("fs");
const { Solution } = require("./Solution");
const {
  compare1DArray,
  convertStringArrToNumArr,
  getArrFromStr,
  printFailedCase,
} = require("../../../javascript/helper");

// Solution function takes 2 arguments, 1st is the array of Numbers and 2nd one is a Number, return type of the function is a Number
let testcases = [];
//  testcases: array of objects
//  {
//      arg1: [],
//      arg2 : Number,
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
      "proddata/data/binary_search/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let input1 = [],
        input2;
      let j = 0,
        input1Str = "",
        input2Str = "";

      while (j < data[i].length && data[i][j] != "]") {
        input1Str += data[i][j];
        j++;
      }
      j++;

      input1Str += "]";

      input1Str = getArrFromStr(input1Str);
      input1 = convertStringArrToNumArr(input1Str);

      while (j < data[i].length) {
        if (!isNaN(data[i][j])) input2Str += data[i][j];
        j++;
      }

      input2 = Number(input2Str.trim());
      // jumps to output of that testcase
      i++;

      let output = Number(data[i].trim());

      j = 0;

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
