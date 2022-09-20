const fs = require("fs");
const { Solution } = require("./Solution");

let testcases = [];
//  testcases: array of objects
//  {
//      arg1: String,
//      output: boolean
// }
let Success = true;
getTestCases();
const sizeOfTestCases = testcases.length;
main();

function main() {
  for (let i = 0; i < sizeOfTestCases; i++) {
    const output_of_user_code = Solution(testcases[i].arg1);

    if (output_of_user_code !== testcases[i].output) {
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
      "proddata/data/valid_palindrome_ii/testcases.txt",
      "utf8"
    );
    data = data.split("\n");
    data.shift();
    for (let i = 0; i < data.length; i++) {
      let inputArr = data[i].trim();
      i++;

      let output = data[i].trim() === "true" ? true : false;

      testcases.push({
        arg1: inputArr,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
