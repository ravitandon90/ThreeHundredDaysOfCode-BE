#include <bits/stdc++.h>
using namespace std;
class Solution
{
public:
    vector<int> moveZeroes(vector<int> &nums)
    {

        int n = nums.size();
        int k = 0;

        for (int i = 0; i < n; i++)
        {
            if (nums[i] != 0)
            {
                nums[k++] = nums[i];
            }
        }
        for (int i = k; i < nums.size(); i++)

            nums[i] = 0;
        return nums;
    }
};