const fs = require("fs");
const Solution = require("./Solution");
const helper = require("../../../javascript/helper");

const compare1DArrayFn = helper.compare1DArray;
const findMaxConsecutiveOnesFn = new Solution().findMaxConsecutiveOnes;

let testcases = [];
//  testcases: array of objects
//  {
//      arg1: [],
//      arg2 : [],
//      output: []
// }
let Success = true;
getTestCases();

const sizeOfTestCases = testcases.length;
main();

function main() {
  for (let i = 0; i < sizeOfTestCases; i++) {
    const user_code_output = findMaxConsecutiveOnesFn(testcases[i].arg1);

    if (user_code_output !== testcases[i].output) {
      console.log("Result: Failed");
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
      "proddata/data/max_consecutive_ones/testcases.txt",
      "utf8"
    );
    data = data.split("\n");
    data.shift();

    for (let i = 0; i < data.length; ) {
      let input1 = [];
      let j = 1;

      while (j < data[i].length && data[i][j] != "]") {
        if (!isNaN(data[i][j])) input1.push(Number(data[i][j]));

        j++;
      }

      i++; // Jump to output line in .txt file
      let output = Number(data[i].trim());

      testcases.push({
        arg1: input1,
        output,
      });

      i++; // Jump to the input and output of next testcases in .txt file
    }
  } catch (err) {
    console.error(err);
  }
}