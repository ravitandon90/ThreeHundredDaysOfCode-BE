from functools import reduce


class Solution:
    def singleNumber(self, nums):
        return reduce(lambda total, el: total ^ el, nums)
