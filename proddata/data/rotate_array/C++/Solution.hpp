#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

class Solution
{
public:
    void rotate(vector<int> &nums, int k)
    {
        if (nums.size() < 2 || k % nums.size() == 0)
            return;
        int count = 0;
        int size = nums.size();
        vector<int> replace(size);
        for (int i = 0; i < size; i++)
        {
            if (i + k > size - 1)
            {
                int remainingSteps = (i + k) - (size - 1);
                while (remainingSteps > size)
                    remainingSteps -= size;
                replace[remainingSteps - 1] = nums[i];
            }
            else
            {
                replace[i + k] = nums[i];
            }
        }
        nums = replace;
    }
};