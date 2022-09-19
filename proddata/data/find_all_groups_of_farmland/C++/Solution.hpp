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
    vector<vector<int>> findFarmland(vector<vector<int>> &land)
    {
        vector<vector<int>> ans;
        for (int y = 0; y < land.size(); y++)
        {
            for (int x = 0; x < land[y].size(); x++)
            {
                if (land[y][x] == 1)
                {
                    if ((x > 0) && (land[y][x - 1] == 1))
                        continue;
                    if ((y > 0) && (land[y - 1][x] == 1))
                        continue;
                    int x1 = x;
                    int y1 = y;
                    while ((x1 < land[y].size()) && (land[y][x1] == 1))
                        x1++;
                    while ((y1 < land.size()) && (land[y1][x] == 1))
                        y1++;
                    ans.push_back(vector<int>{y, x, y1 - 1, x1 - 1});
                }
            }
        }
        return ans;
    }
};