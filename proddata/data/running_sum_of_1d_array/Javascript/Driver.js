const fs = require("fs");
const { Solution } = require("./Solution");
const {
  compare1DArray,
  convertStringArrToNumArr,
  getArrFromStr,
} = require("../../../javascript/helper");

let testcases = [];
//  testcases: array of objects
//  {
//      arg1: [],
//      output: []
// }
let Success = true;
getTestCases();

const sizeOfTestCases = testcases.length;
main();

function main() {
  for (let i = 0; i < sizeOfTestCases; i++) {
    const output_of_user_code = Solution(testcases[i].arg1);

    if (!compare1DArray(output_of_user_code, testcases[i].output)) {
      console.log("Result: Failed");
      console.log("Failed test case:", testcases[i].arg1);
      console.log("Output:", output_of_user_code);
      console.log("Expected:", testcases[i].output);
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
      "proddata/data/running_sum_of_1d_array/testcases.txt",
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

      input1Str = getArrFromStr(input1Str);
      input1 = convertStringArrToNumArr(input1Str);

      // jumps to output of that testcase
      i++;

      let output = getArrFromStr(data[i]);
      output = convertStringArrToNumArr(output);

      testcases.push({
        arg1: input1,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
