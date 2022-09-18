#include <vector>
#include <numeric>
using namespace std;
class Solution
{
public:
    bool solve(vector<int> &nums, int i, int t, int sum, vector<vector<int>> &dp)
    {
        if (t == sum)
        {
            return 1;
        }
        if (i >= nums.size() || t > sum)
        {
            return 0;
        }
        if (dp[i][t] != -1)
            return dp[i][t];
        bool ans1, ans2;
        ans1 = solve(nums, i + 1, t + nums[i], sum, dp);
        ans2 = solve(nums, i + 1, t, sum, dp);
        return dp[i][t] = ans1 || ans2;
    }
    bool canPartition(vector<int> &nums)
    {
        int sum = accumulate(nums.begin(), nums.end(), 0);
        if (sum % 2)
            return 0;
        sum /= 2;
        vector<vector<int>> dp(nums.size() + 1, vector<int>(sum + 1, -1));
        return solve(nums, 0, 0, sum, dp);
    }
};