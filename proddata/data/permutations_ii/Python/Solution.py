class Solution:
    def permuteUnique(self, nums):
        def f(i, arr, res):
            if(i >= len(arr)):
                res.append(arr.copy())
                return
            for j in range(i, len(arr)):
                arr[i], arr[j] = arr[j], arr[i]
                f(i+1, arr, res)
                arr[i], arr[j] = arr[j], arr[i]
            return(res)
        i = 0
        res = []
        f(i, nums, res)
        res = set(tuple(x) for x in res)
        out = []
        for i in res:
            out.append(list(i))

        return(out)
