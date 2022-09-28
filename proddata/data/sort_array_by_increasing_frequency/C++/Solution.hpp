#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

bool func(pair<int, int> a, pair<int, int> b)
{
    if (a.second < b.second)
    {
        return true;
    }
    else if (a.second > b.second)
    {
        return false;
    }
    else
    {
        return a.first >= b.first;
    }
}

class Solution
{
public:
    vector<int> frequencySort(vector<int> &nums)
    {
        int n = nums.size();
        map<int, int> freq;
        for (int i = 0; i < n; i++)
        {
            freq[nums[i]]++;
        }
        vector<pair<int, int>> li;
        for (auto x : freq)
        {
            li.push_back({x.first, x.second});
        }
        sort(li.begin(), li.end(), func);
        vector<int> res;
        for (auto x : li)
        {
            int f = x.first;
            int s = x.second;
            while (s--)
            {
                res.push_back(f);
            }
        }
        return res;
    }
};