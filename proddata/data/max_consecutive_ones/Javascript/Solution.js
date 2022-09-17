class Solution {
  findMaxConsecutiveOnes = function (nums) {
    let ans = 0,
      count = 0;

    for (let i = 0; i < nums.length; i++) {
      if (nums[i]) {
        count++;
        ans = Math.max(ans, count);
      } else count = 0;
    }

    return ans;
  };
}

module.exports = Solution;
