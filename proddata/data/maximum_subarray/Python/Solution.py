from itertools import combinations
from functools import reduce
import operator


class Solution:
    def maxSubArray(self, nums):
        if len(nums) < 2:
            return nums[0]
        else:
            current_sum = nums[0]
            global_sum = nums[0]
            l = len(nums)
            for i in range(1, l):
                current_sum += nums[i]
                if current_sum < nums[i]:
                    current_sum = nums[i]
                if global_sum < current_sum:
                    global_sum = current_sum
                if current_sum < 0:
                    current_sum = 0
            return global_sum
