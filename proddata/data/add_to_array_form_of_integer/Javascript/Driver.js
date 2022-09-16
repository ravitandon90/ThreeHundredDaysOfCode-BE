const fs = require("fs");
const Solution = require("./Solution");
const helper = require("../../../javascript/helper");

const compare1DArrayFn = helper.compare1DArray;
const addToArrayFormFn = new Solution().addToArrayForm;
let testcases = [];
//  testcases: array of objects
//  {
//      arg1: [],
//      arg2 : Number,
//      output: []
// }
let Success = true;
getTestCases();

const sizeOfTestCases = testcases.length;
main();

function main() {
  for (let i = 0; i < sizeOfTestCases; i++) {
    const user_code_output = addToArrayFormFn(
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
    let data = fs.readFileSync("../testcases.txt", "utf8");
    data = data.split("\n");

    for (let i = 0; i < data.length; i += 3) {
      const arrOfStr = data[i].trim().split(" ");
      const arrOfNum = arrOfStr.map((str) => {
        return Number(str);
      });

      const k = Number(data[i + 1]);
      const outputOfStr = data[i + 2].trim().split(" ");
      const outputOfNum = outputOfStr.map((str) => {
        return Number(str);
      });

      testcases.push({
        arg1: arrOfNum,
        arg2: k,
        output: outputOfNum,
      });
    }
  } catch (err) {
    console.error(err);
  }
}