from itertools import combinations
from functools import reduce
import operator


class Solution:
    def subsetXORSum(self, nums):

        return sum(reduce(operator.xor, comb) for i in range(1, len(nums)+1) for comb in combinations(nums, i))
