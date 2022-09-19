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
    vector<vector<int>> floodFill(vector<vector<int>> &image, int sr, int sc, int color)
    {
        int oc = image[sr][sc];
        if (oc != color)
            dfs(sr, sc, oc, color, image);
        return image;
    }
    void dfs(int i, int j, int oc, int nc, vector<vector<int>> &v)
    {
        if (i < 0 || j < 0)
            return;
        if (i >= v.size() || j >= v[0].size())
            return;
        if (oc != v[i][j])
            return;
        v[i][j] = nc;
        dfs(i + 1, j, oc, nc, v);
        dfs(i, j - 1, oc, nc, v);
        dfs(i - 1, j, oc, nc, v);
        dfs(i, j + 1, oc, nc, v);
    }
};