#include <vector>
#include <set>
#include <unordered_map>
#include <string>
#include <algorithm>
using namespace std;

class Solution
{
    bool check(string s, int i, int j)
    {
        while (i < j)
            if (s[i++] != s[j--])
                return false;
        return true;
    }

    void solve(int idx, string s, vector<string> &ans, vector<vector<string>> &output)
    {
        if (idx == s.size())
        {
            if (ans.size() > 0)
                output.push_back(ans);
            return;
        }

        for (int i = idx; i < s.size(); i++)
        {
            if (check(s, idx, i))
            {
                ans.push_back(s.substr(idx, i - idx + 1));
                solve(i + 1, s, ans, output);
                ans.pop_back();
            }
        }
    }

public:
    vector<vector<string>> partition(string s)
    {
        vector<string> ans;
        vector<vector<string>> output;
        solve(0, s, ans, output);
        return output;
    }
};