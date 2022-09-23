#include <vector>
#include <numeric>
using namespace std;
class Solution
{
public:
    void recurs(vector<int> &nums, int i)
    {
        if (i >= nums.size())
            return;
        int maxel = nums[i] + 1;
        int k = i;
        for (int j = i + 1; j < nums.size(); j++)
        {
            if (nums[j] >= maxel)
            {
                maxel = nums[j];
                k = j;
            }
        }
        if (k != i)
        {
            swap(nums[i], nums[k]);
            return;
        }
        recurs(nums, i + 1);
        return;
    }
    int maximumSwap(int num1)
    {
        vector<int> v;
        int num = num1;
        if (num == 0)
            v.push_back(0);
        else
        {
            while (num > 0)
            {
                int rem = num % 10;
                v.push_back(rem);
                num /= 10;
            }
        }
        reverse(v.begin(), v.end());
        recurs(v, 0);
        int res = 0;
        for (auto el : v)
        {
            res = res * 10 + el;
        }
        return res;
    }
};