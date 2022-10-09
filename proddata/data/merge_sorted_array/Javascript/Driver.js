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
//      nums1: [],
//      m: Number,
//      nums2: [],
//      n: Number,
//      output: []
// }

main();

function main() {
  getTestCases();
  let Success = true;
  const sizeOfTestCases = testcases.length;

  for (let i = 0; i < sizeOfTestCases; i++) {
    let nums1 = testcases[i].arg1,
      m = testcases[i].arg2,
      nums2 = testcases[i].arg3,
      n = testcases[i].arg4;
    Solution(nums1, m, nums2, n);

    if (!compare1DArray(nums1, testcases[i].output)) {
      printFailedCase({
        arg1: testcases[i].arg1,
        arg2: testcases[i].arg2,
        arg3: testcases[i].arg3,
        arg4: testcases[i].arg4,
        actual_output: nums1,
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
      "proddata/data/merge_sorted_array/testcases.txt",
      "utf8"
    );
    data = data.split("\n");

    for (let i = 1; i < data.length; i++) {
      let nums1 = "",
        m = "",
        nums2 = "",
        n = "";
      let j = 0;

      while (j < data[i].length && data[i][j] != "]") {
        nums1 += data[i][j];
        j++;
      }
      nums1 += "]";
      // j++;

      while (j < data[i].length && data[i][j] != "[") {
        if (data[i][j] >= "0" && data[i][j] <= "9") {
          m += data[i][j];
        }
        j++;
      }

      while (j < data[i].length && data[i][j] != "]") {
        nums2 += data[i][j];
        j++;
      }
      nums2 += "]";
      j++;

      while (j < data[i].length) {
        if (data[i][j] >= "0" && data[i][j] <= "9") {
          n += data[i][j];
        }
        j++;
      }

      nums1 = getArrFromStr(nums1);
      nums1 = convertStringArrToNumArr(nums1);
      m = Number(m);
      nums2 = getArrFromStr(nums2);
      nums2 = convertStringArrToNumArr(nums2);
      n = Number(n);

      i++;

      let output = getArrFromStr(data[i]);
      output = convertStringArrToNumArr(output);

      testcases.push({
        arg1: nums1,
        arg2: m,
        arg3: nums2,
        arg4: n,
        output: output,
      });
    }
  } catch (err) {
    console.error(err);
  }
}
