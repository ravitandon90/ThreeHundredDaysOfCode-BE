#include <vector>
using namespace std;
class Solution
{
public:
    vector<vector<int>> generate(int numRows)
    {
        vector<vector<int>> res;
        vector<int> prev, next;
        prev.push_back(1);
        res.push_back(prev);
        for (int i = 1; i < numRows; i++)
        {
            next = vector<int>(i + 1, 1);
            for (int j = 1; j < i; j++)
            {
                next[j] = prev[j] + prev[j - 1];
            }
            res.push_back(next);
            prev = next;
        }
        return res;
    }
};