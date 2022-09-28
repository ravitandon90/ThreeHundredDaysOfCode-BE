#include <vector>
#include <numeric>
using namespace std;
class Solution
{
public:
    int thirdMax(vector<int> &nums)
    {
        sort(nums.begin(), nums.end(), greater<int>());
        if (nums.size() < 3)
        {
            return nums[0];
        }
        int m = nums[0];
        int c = 2;
        for (int i = 1; i < nums.size(); i++)
        {
            if (nums[i - 1] != nums[i])
            {
                c--;
            }
            if (c == 0)
            {
                m = nums[i];
                break;
            }
        }
        return m;
    }
};