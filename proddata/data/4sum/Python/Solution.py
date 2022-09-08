class Solution:
    def fourSum(self, arr, target):
        arr.sort()
        res = []
        ans = []
        for i in range(len(arr)-3):
            for j in range(i+1, len(arr)-1):
                l = j+1
                r = len(arr) - 1
                while l < r:
                    if arr[i] + arr[j] + arr[l] + arr[r] == target:
                        res.append((arr[i], arr[j], arr[l], arr[r]))
                        l += 1
                        r -= 1
                    elif arr[i] + arr[j] + arr[l] + arr[r] > target:
                        r -= 1
                    else:
                        l += 1
        for i in list(set(res)):
            ans.append(list(i))
        return ans
