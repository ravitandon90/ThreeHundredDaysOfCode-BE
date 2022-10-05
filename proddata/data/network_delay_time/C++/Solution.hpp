#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;

class Solution
{
public:
    vector<int> Dijkstra(int &n, vector<vector<pair<int, int>>> &graph, int &V)
    {
        vector<int> distanceToLastNode(V + 1, 1e9);
        distanceToLastNode[n] = 0;
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        pq.push({0,
                 n});
        while (!pq.empty())
        {
            int u = pq.top().second;
            int distance = pq.top().first;
            pq.pop();
            for (auto next : graph[u])
            {
                int v = next.first, dis = next.second;
                if (distanceToLastNode[v] > distanceToLastNode[u] + dis)
                {
                    distanceToLastNode[v] = distanceToLastNode[u] + dis;
                    pq.push({distanceToLastNode[v],
                             v});
                }
            }
        }
        return distanceToLastNode;
    }

    int networkDelayTime(vector<vector<int>> &times, int n, int k)
    {

        vector<int> dis(n + 1, 1e9);
        vector<vector<pair<int, int>>> adj(n + 1);

        for (auto t : times)
        {
            adj[t[0]].push_back({t[1],
                                 t[2]});
        }
        dis = Dijkstra(k, adj, n);

        int ans = 0;
        for (int i = 1; i <= n; i++)
        {
            if (dis[i] == 1e9)
                return -1;
            ans = max(ans, dis[i]);
        }
        return ans;
    }
};