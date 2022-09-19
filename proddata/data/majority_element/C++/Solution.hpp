#include <vector>
using namespace std;
class Solution
{
public:
    int majorityElement(vector<int> &nums)
    {
        int count = 0;
        int candidate = 0;
        int x = nums.size();
        for (int i = 0; i < x; i++)
        {

            if (count == 0)
            {
                candidate = nums[i];
            }
            if (i == candidate)
                count += 1;
            else
                count -= 1;
        }
        return candidate;
    }
};