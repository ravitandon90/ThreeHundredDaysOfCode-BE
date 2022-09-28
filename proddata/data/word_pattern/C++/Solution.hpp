#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;
class Solution
{
public:
    bool wordPattern(string pattern, string s)
    {

        vector<string> v;

        string temp = "";

        for (int i = 0; i < s.size(); i++)
        {
            if (!isspace(s[i]))
            {
                temp += s[i];
            }
            else
            {
                if (temp != "")
                {
                    v.push_back(temp);
                    temp = "";
                }
            }
        }

        if (temp != "")
        {
            v.push_back(temp);
            temp = "";
        }

        unordered_map<char, string> mp;
        unordered_map<string, char> mp1;

        if (pattern.size() != v.size())
            return false;

        for (int i = 0; i < pattern.size(); i++)
        {

            if (mp.find(pattern[i]) == mp.end())
            {
                mp[pattern[i]] = v[i];
            }
            else if (mp[pattern[i]] != v[i])
            {
                return false;
            }

            if (mp1.find(v[i]) == mp1.end())
            {
                mp1[v[i]] = pattern[i];
            }
            else if (mp1[v[i]] != pattern[i])
            {
                return false;
            }
        }

        return true;
    }
};