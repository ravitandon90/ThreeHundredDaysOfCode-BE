#include <vector>
#include <queue>
#include <set>
#include <unordered_map>
#include <string>
#include <algorithm>
#include <map>
using namespace std;

class Solution
{
public:
    vector<vector<int>> permuteUnique(vector<int> &a)
    {
        sort(a.begin(), a.end());
        int n = a.size();
        vector<vector<int>> res;
        res.push_back(a);
        while (true)
        {
            int l = n - 1;
            while (l > 0 && a[l - 1] >= a[l])
                l--;
            if (l == 0)
                break;
            int k = l - 1;
            l = n - 1;
            while (a[k] >= a[l])
                l--;
            swap(a[k], a[l]);
            sort(a.begin() + k + 1, a.end());
            res.push_back(a);
        }
        return res;
    }
};