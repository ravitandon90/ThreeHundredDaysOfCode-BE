#include <vector>
#include <numeric>
using namespace std;
class Solution
{
public:
    int minDifference(vector<int> &nums)
    {
        int n = nums.size() - 1;
        if (n <= 3)
            return 0;
        sort(begin(nums), end(nums));
        return min({nums[n] - nums[3],
                    nums[n - 1] - nums[2],
                    nums[n - 2] - nums[1],
                    nums[n - 3] - nums[0]});
    }
};