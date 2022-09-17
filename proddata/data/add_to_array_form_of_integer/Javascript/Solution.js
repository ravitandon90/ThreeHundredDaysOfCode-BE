class Solution {
  addToArrayForm = function (num, K) {
    for (let i = num.length - 1; i >= 0 && K > 0; --i) {
      num[i] += K;
      K = Math.floor(num[i] / 10);
      num[i] %= 10;
    }

    while (K > 0) {
      num = [K % 10, ...num];
      K = Math.floor(K / 10);
    }

    return num;
  };
}

module.exports = Solution;
