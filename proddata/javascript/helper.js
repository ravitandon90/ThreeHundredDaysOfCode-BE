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

module.exports = {
  compare1DArray,
};
