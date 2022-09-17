const fs = require("fs");
const Solution = require("./Solution");
const helper = require("../../../javascript/helper");

const compare1DArrayFn = helper.compare1DArray;
const intersectionFn = new Solution().intersection;

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
    const user_code_output = intersectionFn(
      testcases[i].arg1,
      testcases[i].arg2
    );

    if (!compare1DArrayFn(user_code_output, testcases[i].output)) {
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
      "proddata/data/intersection_of_two_arrays/testcases.txt",
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

      while (j < data[i].length && data[i][j] != "[") {
        j++;
      }
      j++;

      let input2 = [];

      while (j < data[i].length && data[i][j] != "]") {
        if (!isNaN(data[i][j])) input2.push(Number(data[i][j]));
        j++;
      }

      i++; // jump to the next line of testcases.txt

      if (i >= data.length) {
        break;
      }

      let output = [];
      j = 1;

      while (j < data[i].length && data[i][j] != "]") {
        if (!isNaN(data[i][j])) output.push(Number(data[i][j]));
        j++;
      }

      testcases.push({
        arg1: input1,
        arg2: input2,
        output,
      });

      i++; // Jump to the input and output of next testcases in .txt file
    }
  } catch (err) {
    console.error(err);
  }
}