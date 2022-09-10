#include <vector>
#include <queue>
#include <set>
#include <unordered_map>
#include <string>
#include <algorithm>
using namespace std;

class Solution
{
public:
    void find(int idx, vector<int> &ds, vector<int> &nums, int k, vector<vector<int>> &ans)
    {
        if (ds.size() == k)
        {
            ans.push_back(ds);
            return;
        }
        if (idx == nums.size())
            return;
        for (int i = idx; i < nums.size(); i++)
        {
            ds.push_back(nums[i]);
            find(i + 1, ds, nums, k, ans);
            ds.pop_back();
        }
    }
    vector<vector<int>> combine(int n, int k)
    {
        vector<int> nums;
        vector<vector<int>> ans;
        vector<int> ds;
        for (int i = 1; i <= n; i++)
            nums.push_back(i);
        find(0, ds, nums, k, ans);
        return ans;
    }
};