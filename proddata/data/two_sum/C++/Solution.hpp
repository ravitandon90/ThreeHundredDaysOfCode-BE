#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

class Solution
{
public:
    vector<int> twoSum(vector<int> &a, int target)
    {

        vector<pair<int, int>> nums;
        for (int i = 0; i < a.size(); ++i)
            nums.push_back({a[i], i});
        sort(nums.begin(), nums.end());
        vector<int> ans;
        int i = 0, j = nums.size() - 1;
        while (i < j)
        {
            if (nums[i].first + nums[j].first < target)
                ++i;
            else if (nums[i].first + nums[j].first > target)
                --j;
            else
            {
                ans.push_back(nums[i].second);
                ans.push_back(nums[j].second);
                ++i;
            }
        }
        return ans;
    }
};