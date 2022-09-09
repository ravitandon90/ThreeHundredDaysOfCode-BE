#include <vector>
#include <set>
#include <unordered_map>
#include <string>
#include <algorithm>
using namespace std;

class Solution
{
public:
    int find_par(int node, vector<int> &parent)
    {
        if (node == parent[node])
        {
            return node;
        }

        return parent[node] = find_par(parent[node], parent);
    }

    void union_node(int a, int b, vector<int> &size, vector<int> &parent)
    {
        a = find_par(a, parent);
        b = find_par(b, parent);
        if (a == b)
            return;
        if (size[a] < size[b])
        {
            parent[a] = b;
            size[b] += size[a];
        }
        else
        {
            parent[b] = a;
            size[a] += size[b];
        }
        return;
    }

    vector<vector<string>> accountsMerge(vector<vector<string>> &edges)
    {

        sort(edges.begin(), edges.end());

        int n = edges.size();

        vector<int> parent(n, 0), rank(n, 0);

        for (int i = 0; i < n; i++)
        {
            parent[i] = i;
        }

        unordered_map<string, int> mp;

        for (int j = 0; j < n; j++)
        {
            vector<string> t = edges[j];
            for (int i = 1; i < t.size(); i++)
            {
                if (mp.find(t[i]) == mp.end())
                {
                    mp[t[i]] = j;
                }
                else
                {

                    union_node(mp[t[i]], j, rank, parent);
                }
            }
        }

        unordered_map<int, set<string>> st;

        for (int i = 0; i < edges.size(); i++)
        {
            int v = find_par(i, parent);
            for (int j = 1; j < edges[i].size(); j++)
            {
                st[v].insert(edges[i][j]);
            }
        }

        vector<vector<string>> res;

        for (auto it = st.begin(); it != st.end(); it++)
        {
            vector<string> ans;
            ans.push_back(edges[it->first][0]);

            for (auto x : it->second)
            {
                ans.push_back(x);
            }

            res.push_back(ans);
        }

        return res;
    }
};