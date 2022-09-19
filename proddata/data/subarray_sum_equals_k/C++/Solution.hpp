#include <vector>
#include <map>
using namespace std;

class Solution
{
public:
    int subarraySum(vector<int> &nums, int k)
    {

        map<int, int> m;

        int sum = 0, ans = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            sum += nums[i];
            if (sum == k)
            {
                ans++;
            }
            if (m.find(sum - k) != m.end())
            {
                ans += m[sum - k];
            }
            m[sum] += 1;
        }
        return ans;
    }
};