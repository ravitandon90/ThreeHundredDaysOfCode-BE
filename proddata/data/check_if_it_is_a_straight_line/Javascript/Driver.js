const fs = require("fs");
const Solution = require("./Solution");

const checkStraightLineFn = new Solution().checkStraightLine;

let testcases = [];
//  testcases: array of objects
//  {
//      arg1: [],
//      output: boolean
// }
let Success = true;
getTestCases();

const sizeOfTestCases = testcases.length;
main();

function main() {
  for (let i = 0; i < sizeOfTestCases; i++) {
    const user_code_output = checkStraightLineFn(testcases[i].arg1);

    if (user_code_output !== testcases[i].output) {
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
      "proddata/data/check_if_it_is_a_straight_line/testcases.txt",
      "utf8"
    );
    data = data.split("\n");
    data.shift();

    for (let i = 0; i < data.length; ) {
      // traverse vertically (line wise)
      let input1 = [];
      let j = 0;

      while (j < data[i].length) {
        while (j < data[i].length && data[i][j] != "[") j++;
        j++;
        let innerArr = [];

        while (j < data[i].length && data[i][j] != "]") {
          if (!isNaN(data[i][j])) innerArr.push(Number(data[i][j]));

          j++;
        }

        if (innerArr.length) input1.push(innerArr);
      }

      i++; // Jump to output line in .txt file
      let output = data[i].trim() === "true" ? true : false;

      testcases.push({
        arg1: input1,
        output,
      });

      i++; // Jump to the next input of testcases in .txt file
    }
  } catch (err) {
    console.error(err);
  }
}
