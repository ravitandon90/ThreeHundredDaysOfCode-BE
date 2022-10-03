#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <queue>
using namespace std;

class Solution
{
public:
    vector<bool> kidsWithCandies(vector<int> &candies, int extraCandies)
    {
        vector<bool> ans(candies.size(), 0);
        int max = 0;

        for (int i = 0; i < candies.size(); i++)
        {
            if (candies[i] > max)
                max = candies[i];
        }

        for (int i = 0; i < candies.size(); i++)
        {
            if (candies[i] + extraCandies >= max)
                ans[i] = true;
        }
        return ans;
    }
};