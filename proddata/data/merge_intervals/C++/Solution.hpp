#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;

class Solution
{
    public:
        vector<vector < int>> merge(vector<vector < int>> &intervals)
        {
            vector<vector < int>> ans;
            sort(intervals.begin(), intervals.end());
            ans.push_back(intervals[0]);
            for (int i = 1; i < intervals.size(); i++)
            {
                if (intervals[i][0] > ans[ans.size() - 1][1])
                {
                    ans.push_back(intervals[i]);
                }
                else
                {
                    ans[ans.size() - 1][1] = max(intervals[i][1], ans[ans.size() - 1][1]);
                }
            }
            return ans;
        }
};