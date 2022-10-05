#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;

class Solution
{
public:
    int solve(int s, vector<bool> &v, vector<vector<int>> &g)
    {
        int a = 0;
        v[s] = true;
        for (auto &i : g[s])
        {
            if (!v[abs(i)])
                a += solve(abs(i), v, g) + (i > 0);
        }
        return a;
    }
    int minReorder(int &n, vector<vector<int>> &connections)
    {
        vector<bool> v(n, false);
        int ans1 = 0;
        vector<vector<int>> g1(n);
        for (auto &i : connections)
        {
            g1[i[0]].push_back(i[1]);
            g1[i[1]].push_back(-i[0]);
        }
        return solve(0, v, g1);
        ;
    }
};