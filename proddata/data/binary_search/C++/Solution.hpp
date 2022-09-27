#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

class Solution
{
public:
    int helper(int low, int high, vector<int> nums, int t)
    {
        int key = (low + high) / 2;

        if (nums[key] == t)
        {
            return key;
        }
        if (low >= high)
        {
            return -1;
        }
        if (t > nums[key])
        {
            return helper(key + 1, high, nums, t);
        }
        else
        {
            return helper(low, key - 1, nums, t);
        }
    }
    int search(vector<int> &nums, int target)
    {
        return helper(0, nums.size() - 1, nums, target);
    }
};