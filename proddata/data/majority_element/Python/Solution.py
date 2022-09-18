class Solution:
    def majorityElement(self, nums) -> int:
        hash_map = {}
        for num in nums:
            if num in hash_map:
                hash_map[num] += 1
            else:
                hash_map[num] = 1
        return max(hash_map.keys(), key=hash_map.get)
