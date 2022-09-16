#include <vector>
using namespace std;
class Solution
{
public:
    int subsetXORSum(vector<int> &nums)
    {
        const int cases = 1 << nums.size();
        int totalSum = 0;

        for (int i = 1, subsetSum; i < cases; ++i)
        {
            subsetSum = 0;
            for (int j = 0; j < nums.size(); ++j)
            {
                if (i & (1 << j))
                    subsetSum ^= nums[j];
            }
            totalSum += subsetSum;
        }
        return totalSum;
    }
};