#include <iostream>
#include <string>
#include <set>
using namespace std;

class Solution
{
public:
    void perm(string &it, string &tiles, set<string> &ans, vector<int> &vis)
    {

        for (int i = 0; i < tiles.length(); i++)
        {
            if (!vis[i])
            {
                char ch = tiles[i];
                it.push_back(ch);
                ans.insert(it);
                vis[i] = 1;
                perm(it, tiles, ans, vis);
                it.pop_back();
                vis[i] = 0;
            }
        }
    }

    int numTilePossibilities(string tiles)
    {

        vector<int> vis(tiles.length(), 0);
        string it = "";
        set<string> ans;
        perm(it, tiles, ans, vis);
        return ans.size();
    }
};