class Solution:
    def countVowelStrings(self, n: int) -> int:
        dp = [[0 for _ in range(5)] for _ in range(n)]
        for i in range(5):
            dp[0][i] = 1
        for i in range(1, n):
            for j in range(0, 5):
                dp[i][j] = sum(dp[i-1][j:])
        return sum(dp[-1])
