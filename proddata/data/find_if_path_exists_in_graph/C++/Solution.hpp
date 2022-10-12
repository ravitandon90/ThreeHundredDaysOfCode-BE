#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;

class Solution
{
public:
    bool dfs(vector<int> &vis, vector<vector<int>> &edges, int src, int &dest)
    {
        vis[src] = 1;
        if (src == dest)
            return true;
        for (auto it : edges[src])
        {
            if (!vis[it])
            {
                // if(it==dest)return true;
                if (dfs(vis, edges, it, dest))
                    return true;
            }
        }
        return false;
    }

    bool validPath(int n, vector<vector<int>> &edges, int source, int destination)
    {
        vector<vector<int>> graph(n);

        for (auto &edge : edges)
        {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }
        vector<int> vis(n, 0);
        bool ans = dfs(vis, graph, source, destination);
        return ans;
    }
};