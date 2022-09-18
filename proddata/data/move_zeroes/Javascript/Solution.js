class Solution {
  moveZeroes = function (nums) {
    let i = 0,
      j = 0;
    while (i < nums.length) {
      if (nums[i] != 0) {
        let temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        j++;
      }
      i++;
    }
  };
}

module.exports = Solution;
