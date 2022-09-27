
#include <bits/stdc++.h>
using namespace std;
class Solution
{
public:
    vector<int> decompressRLElist(vector<int> &nums)
    {
        vector<int> result;
        for (int i = 0; i < nums.size() - 1; i++)
        {
            int freq = nums[i];
            int val = nums[i + 1];
           
            for (int j = 1; j <= freq; j++)
                result.push_back(val);
            i++;
        }

        return result;
    }
};
