#include <iostream>
using namespace std;

class Solution
{
public:
    int helper(int n)
    {

        if (dp[n] != -1)
            return dp[n];
        else if (n == 0 || n == 1)
            return dp[n] = n;
        else if (n == 2)
            return dp[n] = 1;
        else
            return dp[n] = helper(n - 1) + helper(n - 2) + helper(n - 3);
    }

    vector<int> dp;

    int tribonacci(int n)
    {
        dp.resize(n + 1, -1);
        return helper(n);
    }
};