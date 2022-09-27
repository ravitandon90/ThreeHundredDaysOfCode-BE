#include <iostream>
#include <string>
#include <map>
using namespace std;

class Solution
{
public:
    string sortSentence(string s)
    {

        string ans = "";
        map<int, string> mp;

        for (int i = 0; i < s.length(); i++)
        {

            if (s[i] >= 'a' && s[i] <= 'z' || s[i] >= 'A' && s[i] <= 'Z')
                ans += s[i];

            if (s[i] >= '0' && s[i] <= '9')
            {

                mp[s[i]] = ans;
                ans = "";
            }
        }
        string res = "";

        map<int, string>::iterator it;

        for (it = mp.begin(); it != mp.end(); it++)
        {

            res += it->second;

            res += ' ';
        }

        string res2 = "";

        for (int i = 0; i < res.length() - 1; i++)
        {

            res2 += res[i];
        }

        return res2;
    }
};