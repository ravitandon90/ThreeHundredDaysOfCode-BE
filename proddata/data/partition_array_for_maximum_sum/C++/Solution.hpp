#include <vector>
using namespace std;

class Solution
{
public:
    int solve(int i, vector<int> &v, int &k, int &n, vector<int> &dp)
    {
        if (i == n)
            return 0;
        int mx = 0, ans = 0, len = 0;
        if (dp[i] != -1)
            return dp[i];
        for (int j = i; j < min(n, (i + k)); j++)
        {
            len++;
            mx = max(mx, v[j]);
            int sum = mx * len + solve(j + 1, v, k, n, dp);
            ans = max(ans, sum);
        }
        return dp[i] = ans;
    }
    int maxSumAfterPartitioning(vector<int> &arr, int &k)
    {
        int n = arr.size();
        vector<int> v(n, -1);
        return solve(0, arr, k, n, v);
    }
};