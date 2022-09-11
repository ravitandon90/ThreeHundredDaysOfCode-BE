class Solution:
    def subsets(self, nums):
        res = []

        def dfs(i, path):
            if i == len(nums):
                res.append(path)
                return
            dfs(i+1, path)
            dfs(i+1, path+[nums[i]])

        dfs(0, [])
        return res
