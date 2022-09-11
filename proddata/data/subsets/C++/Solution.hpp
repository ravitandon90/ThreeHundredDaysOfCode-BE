#include <vector>
#include <queue>
#include <set>
#include <unordered_map>
#include <string>
#include <algorithm>
#include <map>
using namespace std;

class Solution
{
private:
    void pushin(int ind, vector<vector<int>> &ans, vector<int> &ds, vector<int> nums, int n)
    {
        if (ind >= n)
        {
            ans.push_back(ds);
            return;
        }

        ds.push_back(nums[ind]);
        pushin(ind + 1, ans, ds, nums, n);
        ds.pop_back();
        pushin(ind + 1, ans, ds, nums, n);
    }

public:
    vector<vector<int>> subsets(vector<int> &nums)
    {
        vector<vector<int>> ans;
        vector<int> ds;
        int n = nums.size();
        pushin(0, ans, ds, nums, n);

        return ans;
    }
};