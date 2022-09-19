const fs = require("fs");
const { Solution } = require("./Solution");
const {
  compare1DArray,
  convertStringArrToNumArr,
  getArrFromStr,
} = require("../../../javascript/helper");

// const addToArrayFormFn = new Solution().addToArrayForm;
let testcases = [];
//  testcases: array of objects
//  {
//      arg1: [],
//      arg2 : [],
//      output: []
// }
let Success = true;
getTestCases();
console.log(testcases);

const sizeOfTestCases = testcases.length;
main();

function main() {
  for (let i = 0; i < sizeOfTestCases; i++) {
    const user_code_output = Solution(testcases[i].arg1, testcases[i].arg2);

    if (!compare1DArray(user_code_output, testcases[i].output)) {
      console.log("Result: Failed");
      console.log("Failed test case:", testcases[i].arg1, testcases[i].arg2);
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
      "proddata/data/create_target_array_in_the_given_order/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let input1 = [],
        input2 = [];
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

      while (j < data[i].length && data[i][j] != "]") {
        input2Str += data[i][j];
        j++;
      }

      input2Str += "]";

      input2Str = getArrFromStr(input2Str.trim());
      input2 = convertStringArrToNumArr(input2Str);
      // jumps to output of that testcase
      i++;

      let output = getArrFromStr(data[i]);
      output = convertStringArrToNumArr(output);

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
