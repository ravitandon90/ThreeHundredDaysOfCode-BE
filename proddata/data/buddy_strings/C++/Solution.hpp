#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    bool buddyStrings(string s, string g)
    {
        if (s.length() != g.length())
            return false;
        char a, b;
        int f = 0, d = 0;
        unordered_set<char> m;

        for (int i = 0; i < s.length(); i++)
        {
            if (m.find(s[i]) != m.end())
            {
                d = 1;
            }
            else
                m.insert(s[i]);

            if (s[i] != g[i])
            {
                if (f == 0)
                {
                    a = s[i];
                    b = g[i];
                }
                else if (f == 1)
                {
                    if (g[i] != a || s[i] != b)
                        return false;
                }
                else
                    return false;
                f++;
            }
        }
        if (f == 0 && d == 1)
            return true;
        if (f != 2)
            return false;
        return true;
    }
};