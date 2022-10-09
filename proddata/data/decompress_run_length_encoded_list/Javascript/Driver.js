const fs = require("fs");
const { Solution } = require("./Solution");
const {
  compare1DArray,
  convertStringArrToNumArr,
  getArrFromStr,
  printFailedCase,
} = require("../../../Javascript/helper");

let testcases = [];
//  testcases: array of objects
//  {
//      arg1: [],
//      output: []
// }

main();

function main() {
  getTestCases();
  let Success = true;
  const sizeOfTestCases = testcases.length;

  for (let i = 0; i < sizeOfTestCases; i++) {
    let arg1 = testcases[i].arg1;
    const user_code_output = Solution(arg1);

    if (!compare1DArray(user_code_output, testcases[i].output)) {
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
      "proddata/data/decompress_run_length_encoded_list/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let input = getArrFromStr(data[i]);
      input = convertStringArrToNumArr(input);
      i++;

      let output = getArrFromStr(data[i]);
      output = convertStringArrToNumArr(output);

      testcases.push({
        arg1: input,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
