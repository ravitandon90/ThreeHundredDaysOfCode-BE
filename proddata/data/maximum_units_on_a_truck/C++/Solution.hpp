#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;
class Solution
{
public:
    int maximumUnits(vector<vector<int>> &boxes, int truckSize)
    {
        int res = 0, currBatch;
        sort(begin(boxes), end(boxes), [](auto &a, auto &b)
             { return a[1] > b[1]; });
        for (auto &el : boxes)
        {
            currBatch = min(el[0], truckSize);
            truckSize -= currBatch;
            res += currBatch * el[1];
            if (!truckSize)
                break;
        }
        return res;
    }
};