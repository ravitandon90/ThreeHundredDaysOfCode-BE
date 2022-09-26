#include <vector>
#include <numeric>
using namespace std;
class Solution
{
public:
    bool isMonotonic(vector<int> &nums)
    {
        int f1 = 1, f2 = 1;
        for (int i = 0; i < nums.size() - 1; i++)
        {
            if (nums[i] > nums[i + 1])
                f1 = 0;
            if (nums[i] < nums[i + 1])
                f2 = 0;
        }
        return f1 | f2;
    }
};