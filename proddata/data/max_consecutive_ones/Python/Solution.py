class Solution:
    def findMaxConsecutiveOnes(self, nums):
        maxConsecutiveOnes = currentMaxLength = 0
        for num in nums:
            if num == 0:
                maxConsecutiveOnes = max(maxConsecutiveOnes, currentMaxLength)
                currentMaxLength = 0
            else:
                currentMaxLength += 1
        return max(maxConsecutiveOnes, currentMaxLength)
