const fs = require("fs");
const { Solution } = require("./Solution");
const {
  printFailedCase,
  createTreeFromArray,
} = require("../../../Javascript/helper");

let testcases = [];
//  testcases: array of objects
//  {
//      arg1: treeRoot,
//      output: boolean
// }

main();

function main() {
  getTestCases();

  let Success = true;
  const sizeOfTestCases = testcases.length;

  for (let i = 0; i < sizeOfTestCases; i++) {
    let root1 = createTreeFromArray({
      array: testcases[i].arg1,
      traverseType: "levelOrder",
    });
    const user_code_output = Solution(root1);

    if (user_code_output !== testcases[i].output) {
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
      "proddata/data/balanced_binary_tree/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let input1 = "";
      let s = data[i];
      s = s.split("");
      s.shift();
      s.pop();
      s = s.join("");
      s = s.split(",");
      i++;
      let output = data[i].trim() === "true" ? true : false;

      testcases.push({
        arg1: input1,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
