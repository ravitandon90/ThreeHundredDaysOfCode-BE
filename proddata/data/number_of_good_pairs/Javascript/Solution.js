class Solution {
  numIdenticalPairs = function (nums) {
    let obj = {};
    let ans = 0;

    for (let i = 0; i < nums.length; i++) {
      obj[nums[i]] ? obj[nums[i]]++ : (obj[nums[i]] = 1);
    }

    Object.values(obj).map((val) => {
      ans += Math.floor((val * (val - 1)) / 2);
    });

    return ans;
  };
}

module.exports = Solution;
