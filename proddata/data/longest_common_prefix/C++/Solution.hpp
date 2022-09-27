#include <vector>
#include <numeric>
#include <algorithm>
using namespace std;
class Solution
{
public:
    string longestCommonPrefix(vector<string> &strs)
    {
        string ans = strs[0];
        int n = strs.size();
        for (int i = 1; i < n; i++)
        {
            int p = ans.size();
            for (int j = 0; j < p; j++)
            {
                if (strs[i][j] != ans[j])
                {
                    ans = ans.substr(0, j);
                    break;
                }
            }
        }
        return ans;
    }
};