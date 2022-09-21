#include <vector>
using namespace std;
class Solution
{
public:
    int maxSubArray(vector<int> &nums)
    {
        int maxi, curr = 0;
        maxi = INT_MIN;
        for (auto it : nums)
        {
            curr += it;
            maxi = max(maxi, curr);
            curr = max(curr, 0);
        }
        return maxi;
    }
};