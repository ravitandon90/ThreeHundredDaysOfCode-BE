#include <vector>
#include <numeric>
#include <set>
using namespace std;
class Solution
{
public:
    bool containsDuplicate(vector<int> &nums)
    {
        set<int> arr;
        int x = nums.size();
        for (int i = 0; i < nums.size(); i++)
        {
            arr.insert(nums[i]);
        }
        int y = arr.size();
        if (y < x)
            return 1;
        else
            return 0;
    }
};