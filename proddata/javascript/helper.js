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

module.exports = {
  compare1DArray,
  convertStringArrToNumArr,
  getArrFromStr,
};
