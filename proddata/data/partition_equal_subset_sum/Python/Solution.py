from itertools import combinations
from functools import reduce
import operator


class Solution:
    def canPartition(self, nums) -> bool:
        if len(nums) <= 1:
            return False
        sumList = {nums[0]}
        for num in nums[1:]:
            newSumList = set()
            for elem in sumList:
                newSumList.add(abs(num - elem))
                newSumList.add(num + elem)
            sumList = newSumList
        return 0 in sumList
