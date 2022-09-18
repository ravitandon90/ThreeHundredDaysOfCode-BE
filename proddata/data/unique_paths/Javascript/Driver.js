const fs = require("fs");
const { Solution } = require("./Solution");

let testcases = [];
//  testcases: array of objects
//  {
//      arg1: Number,
//      arg2: Number,
//      output: Number
// }
let Success = true;
getTestCases();
const sizeOfTestCases = testcases.length;
main();

function main() {
  for (let i = 0; i < sizeOfTestCases; i++) {
    const output_of_user_code = Solution(testcases[i].arg1, testcases[i].arg2);

    if (output_of_user_code !== testcases[i].output) {
      console.log("Result: Failed");
      console.log("Failed test case:", testcases[i].arg1, testcases[i].arg2);
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
      "proddata/data/unique_paths/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 0; i < data.length; i++) {
      let inputArr = data[i].trim().split(" ");
      i++;

      let output = data[i] === "true" ? true : false;
      let input1 = Number(inputArr[0]),
        input2 = Number(inputArr[inputArr.length - 1]);

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
