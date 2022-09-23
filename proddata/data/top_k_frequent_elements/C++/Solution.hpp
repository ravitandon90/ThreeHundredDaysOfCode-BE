#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

class Solution
{
public:
    vector<int> topKFrequent(vector<int> &nums, int k)
    {
        unordered_map<int, int> mp;
        for (int i = 0; i < nums.size(); i++)
        {
            mp[nums[i]]++;
        }
        sort(nums.begin(), nums.end(), [&](int l, int r)
             {
                if (mp[l] != mp[r]) return mp[l] > mp[r];
                else return l > r; });
        vector<int> ans;
        ans.push_back(nums[0]);
        int i = 1;
        k--;
        while (k)
        {
            if (nums[i] != nums[i - 1])
            {
                ans.push_back(nums[i]);
                k--;
                i++;
            }
            else
            {
                i++;
            }
        }

        return ans;
    }
};