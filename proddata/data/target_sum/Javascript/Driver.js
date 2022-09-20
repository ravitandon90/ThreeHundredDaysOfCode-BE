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
//      arg2 : Number,
//      output: Number
// }
let Success = true;
getTestCases();

const sizeOfTestCases = testcases.length;
main();

function main() {
  for (let i = 0; i < sizeOfTestCases; i++) {
    const output_of_users_code = Solution(testcases[i].arg1, testcases[i].arg2);

    if (output_of_users_code !== testcases[i].output) {
      console.log("Result: Failed");
      console.log("Failed test case:", testcases[i].arg1, testcases[i].arg2);
      console.log("Output:", output_of_users_code);
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
      "proddata/data/target_sum/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 0; i < data.length; i++) {
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

      input2 = Number(input2Str);
      // jumps to output of that testcase
      i++;

      let output = Number(data[i].trim());

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
