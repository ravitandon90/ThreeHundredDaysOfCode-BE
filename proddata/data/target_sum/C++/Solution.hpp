#include <vector>
#include <map>
using namespace std;

class Solution
{
public:
    int findTargetSumWays(vector<int> &nums, int target)
    {
        int n = nums.size();
        int total_sum = 0;
        for (int i = 0; i < n; i++)
            total_sum += nums[i];
        int T = target + total_sum;
        if (T % 2 || T < 0)
            return 0;
        T /= 2;
        vector<vector<int>> dp(n + 1, vector<int>(T + 1, 0));
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++)
        {
            for (int tar = 0; tar <= T; tar++)
            {
                int pick = 0, notpick = 0;
                notpick = dp[i - 1][tar];
                if (tar >= nums[i - 1])
                    pick = dp[i - 1][tar - nums[i - 1]];
                dp[i][tar] = pick + notpick;
            }
        }
        return dp[n][T];
    }
};