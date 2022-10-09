#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;

class Solution
{
public:
    vector<string> wordBreak(string s, vector<string> &wordDict)
    {
        vector<vector<string>> ans(s.size() + 1);
        ans[0] = {""};

        for (int i = 1; i <= s.length(); i++)
        {
            for (int j = 0; j < wordDict.size(); j++)
            {
                string key = wordDict[j];
                if (i < key.size())
                    continue;
                string tmp = s.substr(i - key.size(), key.size()), container;
                if (tmp == key)
                {
                    for (int k = 0; k < ans[i - key.size()].size(); k++)
                    {
                        container = key;
                        if (ans[i - key.size()][k] != "")
                            container = ans[i - key.size()][k] + " " + key;
                        ans[i].push_back(container);
                    }
                }
            }
        }

        return ans[s.size()];
    }
};