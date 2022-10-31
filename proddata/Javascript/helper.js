class Node {
  constructor(val) {
    this.left = null;
    this.right = null;
    this.val = val;
  }
}

// Function to insert nodes in level order
function insertLevelOrder(arr, i) {
  let root = null;
  // Base case for recursion
  if (i < arr.length) {
    root = new Node(arr[i]);

    // insert left child
    root.left = insertLevelOrder(arr, 2 * i + 1);

    // insert right child
    root.right = insertLevelOrder(arr, 2 * i + 2);
  }
  return root;
}

function createTreeFromArray({ array, traversalType }) {
  let root;
  if (traversalType === "levelOrder") root = insertLevelOrder(array, 0);

  return root;
}

function compare1DArray(arr1 = [], arr2 = []) {
  if (arr1.length != arr2.length) {
    return false;
  }

  let n = arr1.length;

  for (let i = 0; i < n; i++) {
    if (arr1[i] !== arr2[i]) {
      return false;
    }
  }

  return true;
}

// converts string elements of array to number and returns a new array
function convertStringArrToNumArr(strArr) {
  let arr = [];
  arr = strArr.map((val) => {
    return Number(val);
  });

  return arr;
}

// converts string into array
function getArrFromStr(str) {
  let i = 0,
    arr = [];
  while (i < str.length && str[i] != "[") {
    i++;
  }
  i++;

  while (i < str.length && str[i] != "]") {
    if (str[i] == " " || str[i] == ",") {
    } else arr.push(str[i]);

    i++;
  }

  return arr;
}

// prints failed case, takes all the input, actual output and expected output as an argument
// argument passed must be an object with above mentioned properties
function printFailedCase({
  arg1,
  arg2,
  arg3,
  arg4 = null,
  actual_output,
  expected_output,
}) {
  console.log("Result: Failed");
  console.log(
    "Failed test case:",
    arg1 !== null ? arg1 : "",
    arg2 !== null ? arg2 : "",
    arg3 !== null ? arg3 : "",
    arg4 !== null ? arg4 : ""
  );
  console.log("Output:", actual_output);
  console.log("Expected:", expected_output);
}

module.exports = {
  compare1DArray,
  convertStringArrToNumArr,
  getArrFromStr,
  printFailedCase,
  createTreeFromArray,
};
