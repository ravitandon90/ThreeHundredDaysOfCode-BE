class Solution:
    def combine(self, n, k):
        def f(i, n, k, sub, ans):
            if(k == 0):
                ans.append(sub.copy())
                return
            if(i > n):
                return
            sub.append(i)
            f(i+1, n, k-1, sub, ans)
            sub.pop()
            f(i+1, n, k, sub, ans)
        i = 1
        ans = []
        sub = []
        f(i, n, k, sub, ans)
        return(ans)
