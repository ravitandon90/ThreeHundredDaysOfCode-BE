#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

class Solution
{
public:
    int findKthLargest(vector<int> &nums, int k)
    {
        int i = 0;
        if (nums.size() == 1)
            return nums[0];
        sort(nums.begin(), nums.end());
        reverse(nums.begin(), nums.end());
        for (i = 0; i < k; i++)
        {
            if (i = k - 1)
                return nums[i];
        }
        return nums[i - 1];
    }
};