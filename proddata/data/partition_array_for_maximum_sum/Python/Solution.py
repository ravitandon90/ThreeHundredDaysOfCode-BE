class Solution:
    def maxSumAfterPartitioning(self, arr, k):
        n = len(arr)
        dp = [0] * (n+1)

        for i in range(1, n+1):
            for j in range(1, k+1):
                if i - j >= 0:
                    dp[i] = max(
                        dp[i-j] + max(arr[i-j: i]) * j,
                        dp[i]
                    )
        return dp[-1]
