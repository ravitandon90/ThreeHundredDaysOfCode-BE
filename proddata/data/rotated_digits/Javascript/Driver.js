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
//      arg1: Number,
//      output: Number
// }
let Success = true;
getTestCases();

const sizeOfTestCases = testcases.length;
main();

function main() {
  for (let i = 0; i < sizeOfTestCases; i++) {
    const user_code_output = Solution(testcases[i].arg1);

    if (user_code_output !== testcases[i].output) {
      console.log("Result: Failed");
      console.log("Failed test case:", testcases[i].arg1);
      console.log("Output:", user_code_output);
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
      "proddata/data/rotated_digits/testcases.txt",
      "utf8"
    );
    data = data.split("\n");
    data.shift();

    for (let i = 0; i < data.length; i++) {
      let input1 = Number(data[i].trim());
      // jumps to output of that testcase
      i++;

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
