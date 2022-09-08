class Solution:
    def threeSum(self, nums):
        if len(nums) < 3:
            return []
        nums.sort()
        ans = []
        for cur_ind in range(len(nums)-1):
            if cur_ind > 0 and nums[cur_ind] == nums[cur_ind - 1]:
                continue
            left, right = cur_ind + 1, len(nums)-1
            while left < right:
                threesum = nums[cur_ind] + nums[left] + nums[right]
                if threesum > 0:
                    right -= 1
                elif threesum < 0:
                    left += 1
                else:
                    ans.append([nums[cur_ind], nums[left], nums[right]])
                    left += 1
                    while nums[left] == nums[left - 1] and left < right:
                        left += 1
        return ans
