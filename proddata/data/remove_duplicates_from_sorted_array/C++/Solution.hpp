#include <vector>
#include <numeric>
using namespace std;
class Solution
{
public:
    int removeDuplicates(vector<int> &nums)
    {
        int n = nums.size();
        if (n == 1)
            return 1;
        int i = 0, j = 0;
        int count = 1;
        for (int i = 0; i < n - 1; ++i)
        {
            if (nums[i] == nums[i + 1])
            {
                continue;
            }
            else if (nums[i] != nums[i + 1])
            {
                j++;
                nums[j] = nums[i + 1];
                count++;
            }
        }
        return count;
    }
};