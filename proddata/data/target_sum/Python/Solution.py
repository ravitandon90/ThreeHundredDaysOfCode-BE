class Solution:
    def findTargetSumWays(self, nums, target: int) -> int:
        def func(idx, sum1):
            if idx == len(nums) and sum1 == target:
                return 1
            elif idx == len(nums) and sum1 != target:
                return 0
            elif (idx, sum1) in dp:
                return dp[(idx, sum1)]
            dp[(idx, sum1)] = func(idx+1, sum1-nums[idx]) + \
                func(idx+1, sum1+nums[idx])
            return dp[(idx, sum1)]
        dp = {}
        return func(0, 0)
