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
  function printFailedCase({ arg1, arg2, arg3, actual_output, expected_output }) {
    console.log("Result: Failed");
    console.log(
      "Failed test case:",
      arg1 !== null ? arg1 : "",
      arg2 !== null ? arg2 : "",
      arg3 !== null ? arg3 : ""
    );
    console.log("Output:", actual_output);
    console.log("Expected:", expected_output);
  }
  
  module.exports = {
    compare1DArray,
    convertStringArrToNumArr,
    getArrFromStr,
    printFailedCase,
  };
  