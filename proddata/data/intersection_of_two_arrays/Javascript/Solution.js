class Solution {
  intersection = function (nums1, nums2) {
    let set = new Set();
    nums1.map((val) => set.add(val));
    let ans = [];

    nums2.map((val) => {
      if (set.has(val)) {
        set.delete(val);
        ans.push(val);
      }
    });

    return ans;
  };
}

module.exports = Solution;
