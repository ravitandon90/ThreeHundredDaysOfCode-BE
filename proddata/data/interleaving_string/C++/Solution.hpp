#include <iostream>
#include <string>
using namespace std;

class Solution
{
    bool solve(string s1, string s2, string s3, int i, int j, int n1, int n2, int n3, int pos, vector<vector<int>> &dp)
    {
        if (pos == n3)
        {
            return dp[i][j] = true;
        }
        if (dp[i][j] != -1)
        {
            return dp[i][j];
        }
        if (s1[i] == s3[pos] && s2[j] == s3[pos])
        {
            return dp[i][j] = (solve(s1, s2, s3, i + 1, j, n1, n2, n3, pos + 1, dp) || solve(s1, s2, s3, i, j + 1, n1, n2, n3, pos + 1, dp));
        }
        else if (s1[i] == s3[pos] && i < n1)
        {
            return dp[i][j] = solve(s1, s2, s3, i + 1, j, n1, n2, n3, pos + 1, dp);
        }
        else if (s2[j] == s3[pos] && j < n2)
        {
            return dp[i][j] = solve(s1, s2, s3, i, j + 1, n1, n2, n3, pos + 1, dp);
        }
        return false;
    }

public:
    bool isInterleave(string s1, string s2, string s3)
    {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        vector<vector<int>> dp(n1 + 1, vector<int>(n2 + 1, -1));
        if (n1 + n2 != n3)
        {
            return false;
        }
        return solve(s1, s2, s3, 0, 0, n1, n2, n3, 0, dp);
    }
};