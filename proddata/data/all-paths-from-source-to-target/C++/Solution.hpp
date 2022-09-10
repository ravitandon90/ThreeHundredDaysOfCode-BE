#include <vector>
#include <queue>
#include <set>
#include <unordered_map>
#include <string>
#include <algorithm>
using namespace std;

class Solution
{
public:
    vector<vector<int>> allPathsSourceTarget(vector<vector<int>> &graph)
    {
        vector<vector<int>> result;
        queue<vector<int>> q;
        int n = graph.size() - 1;
        q.push(vector<int>{0});
        while (q.size() > 0)
        {
            auto cur = q.front();
            auto next = graph[cur[cur.size() - 1]];
            q.pop();
            if (next.size() > 0)
            {
                for (int i = 0; i < next.size(); i++)
                {
                    auto newVec = cur;
                    newVec.push_back(next[i]);
                    if (next[i] == n)
                    {
                        result.push_back(newVec);
                    }
                    else
                    {
                        q.push(newVec);
                    }
                }
            }
        }

        return result;
    }
};