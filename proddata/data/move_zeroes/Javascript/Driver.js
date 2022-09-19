const fs = require("fs");
const Solution = require("./Solution");
const helper = require("../../../javascript/helper");

const compare1DArrayFn = helper.compare1DArray;
const moveZeroesFn = new Solution().moveZeroes;
let testcases = [];
//  testcases: array of objects
//  {
//      arg1: [],
//      output: []
// }
let Success = true;
getTestCases();
const sizeOfTestCases = testcases.length;
main();

function main() {
  for (let i = 0; i < sizeOfTestCases; i++) {
    let inputArr = [...testcases[i].arg1];
    moveZeroesFn(inputArr);

    if (!compare1DArrayFn(inputArr, testcases[i].output)) {
      console.log("Result: Failed");
      console.log("Failed testcase:", testcases[i].arg1);
      console.log("Output:", inputArr);
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
      "proddata/data/move_zeroes/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i += 2) {
      const arrOfNum = [...getArrFromStr(data[i].trim())];

      const outputOfNum = [...getArrFromStr(data[i + 1].trim())];

      testcases.push({
        arg1: arrOfNum,
        output: outputOfNum,
      });
    }
  } catch (err) {
    console.error(err);
  }
}

// str should be of form: '[1, 2, 3, 4]'
function getArrFromStr(str) {
  let i = 1;
  let arr = [];

  while (i < str.length && str[i] != "]") {
    if (!isNaN(str[i])) {
      arr.push(Number(str[i]));
    }

    i++;
  }

  return arr;
}
