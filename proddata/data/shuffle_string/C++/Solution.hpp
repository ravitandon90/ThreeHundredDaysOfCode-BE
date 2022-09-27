#include <vector>
#include <numeric>
#include <map>
using namespace std;
class Solution
{
public:
    string restoreString(string s, vector<int> &indices)
    {
        map<int, char> mp;

        for (int i = 0; i < indices.size(); i++)
        {
            mp[indices[i]] = s[i];
        }
        string k = "";
        for (auto j : mp)
        {
            k += j.second;
        }
        return k;
    }
};