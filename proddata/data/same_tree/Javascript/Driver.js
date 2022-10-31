const fs = require("fs");
const { Solution } = require("./Solution");
const {
  printFailedCase,
  convertStringArrToNumArr,
  getArrFromStr,
} = require("../../../Javascript/helper");

let testcases = [];
//  testcases: array of objects
//  {
//      arg1: treeRoot,
//      arg2: treeRoot,
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
    let root2 = createTreeFromArray({
      array: testcases[i].arg2,
      traverseType: "levelOrder",
    });
    const user_code_output = Solution(root1, root2);

    if (user_code_output !== testcases[i].output) {
      printFailedCase({
        arg1: testcases[i].arg1,
        arg2: testcases[i].arg2,
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
    let data = fs.readFileSync("proddata/data/same_tree/testcases.txt", "utf8");
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let input1 = "";
      let j = 0;
      data[i] = data[i].trim();

      while (j < data[i].length && j !== "]") {
        input1 += data[i][j];
      }
      j++;
      input1 += "]";

      let input2 = "";
      while (j < data[i].length && j != "]") {
        input2 += data[i][j];
      }

      input2 += "]";
      i++;
      let output = data[i].trim() === "true" ? true : false;
      input1 = getArrFromStr(input1);
      input1 = convertStringArrToNumArr(input1);
      input2 = getArrFromStr(input2);
      input2 = convertStringArrToNumArr(input2);

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
