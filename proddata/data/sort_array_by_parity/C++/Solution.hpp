#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

class Solution
{
public:
    vector<int> sortArrayByParity(vector<int> &nums)
    {
        int index = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            if (nums[i] % 2 == 0)
            {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index += 1;
            }
        }
        return nums;
    }
};