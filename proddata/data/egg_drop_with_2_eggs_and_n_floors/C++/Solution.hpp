#include <iostream>
using namespace std;

class Solution
{
public:
    int twoEggDrop(int n)
    {

        vector<int> dp(n + 1, 0);
        int maxi, mini;

        for (int i = 1; i <= n; i++)
        {
            mini = INT_MAX;
            for (int mj = i - 1, pj = 0; mj >= 0; mj--, pj++)
            {
                maxi = max(dp[mj], pj);

                mini = min(mini, maxi);
            }
            dp[i] = mini + 1;
        }

        return dp[n];
    }
};