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
    int islandPerimeter(vector<vector<int>> &grid)
    {
        int countones = 0, repeat = 0;
        for (int i = 0; i < grid.size(); i++)
        {
            for (int j = 0; j < grid[i].size(); j++)
            {
                if (grid[i][j] == 1)
                {
                    countones++;
                    if (i != 0 && grid[i - 1][j] == 1)
                        repeat++;
                    if (j != 0 && grid[i][j - 1] == 1)
                        repeat++;
                }
            }
        }
        return 4 * countones - 2 * repeat;
    }
};