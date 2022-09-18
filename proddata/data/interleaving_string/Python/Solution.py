class Solution(object):
    def isInterleave(self, s1, s2, s3):
        n, m = len(s1), len(s2)
        if n+m != len(s3):
            return False
        prev_dp = [False]*(m+1)
        dp = [False]*(m+1)
        prev_dp[0] = True
        for j in range(1, m+1):
            prev_dp[j] = s2[j-1] == s3[j-1] and prev_dp[j-1]
        for i in range(1, n+1):
            dp[0] = s1[i-1] == s3[i-1] and prev_dp[0]
            for j in range(1, m+1):
                check_s1 = s1[i-1] == s3[i+j-1] and prev_dp[j]
                check_s2 = s2[j-1] == s3[i+j-1] and dp[j-1]
                dp[j] = check_s1 or check_s2
            prev_dp = dp
            dp = [False]*(m+1)
        return prev_dp[m]
