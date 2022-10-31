const fs = require("fs");
const { Solution } = require("./Solution");
const {
  printFailedCase,
  convertStringArrToNumArr,
  getArrFromStr,
  createTreeFromArray,
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
    let low = testcases[i].arg2,
      high = testcases[i].arg3;

    const user_code_output = Solution(root1, low, high);

    if (user_code_output !== testcases[i].output) {
      printFailedCase({
        arg1: testcases[i].arg1,
        arg2: testcases[i].arg2,
        arg3: testcases[i].arg3,
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
      "proddata/data/range_sum_of_bst/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let input1 = "";
      let j = 0;
      data[i] = data[i].trim();

      while (j < data[i].length && data[i][j] !== "]") {
        input1 += data[i][j];
      }
      input1 += "]";

      j++;
      while (j < data[i].length && data[i][j] !== ",") {
        j++;
      }
      j++;

      let low = "";
      while (j < data[i].length && data[i][j] != ",") {
        if (!isNaN(data[i][j])) low += data[i][j];
      }
      low = Number(low);
      j++;
      let high = "";
      while (j < data[i].length && data[i][j] != ",") {
        if (!isNaN(data[i][j])) high += data[i][j];
      }
      high = Number(high);
      i++;
      let output = Number(data[i].trim());
      input1 = getArrFromStr(input1);
      input1 = convertStringArrToNumArr(input1);

      testcases.push({
        arg1: input1,
        arg2: low,
        arg3: high,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
