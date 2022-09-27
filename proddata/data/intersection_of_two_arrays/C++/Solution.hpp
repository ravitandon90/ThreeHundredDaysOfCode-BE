#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    vector<int> intersection(vector<int> &nums1, vector<int> &nums2)
    {
        
        vector<int> result;
        map<int, int> mp;

        for (int i = 0; i < nums1.size(); i++)
        {
            int x = nums1[i];
            for (int j = 0; j < nums2.size(); j++)
            {
                if (x == nums2[j])
                    result.push_back(x);
            }
        }
        sort(result.begin(), result.end());
        for (int i = 0; i < result.size(); i++)
        {
            mp[result[i]]++;
     
        }
        vector<int> result1;
        for (auto i : mp)
        {
            if (i.second != 0)
                result1.push_back(i.first);
        }

        return result1;
    }
};
