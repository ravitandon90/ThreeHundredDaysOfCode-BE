class Solution:
    def missingNumber(self, nums) -> int:
        n = len(nums)

        for i in range(n):
            temp = nums[i]
            while temp < n and temp != i:
                nums[temp], nums[i] = nums[i], nums[temp]
                temp = nums[i]

        for i in range(n):
            if nums[i] != i:
                return i
        return n
