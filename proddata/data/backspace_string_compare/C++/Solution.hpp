#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    bool backspaceCompare(string s, string t)
    {
        string as = "", at = "";
        int hold = 0;
        for (int i = s.size() - 1; i >= 0; i--)
        {
            if (s[i] == '#')
                hold++;
            else
            {
                if (hold == 0)
                {
                    as += s[i];
                }
                else
                {
                    hold--;
                }
            }
        }
        hold = 0;
        for (int i = t.size() - 1; i >= 0; i--)
        {
            if (t[i] == '#')
                hold++;
            else
            {
                if (hold == 0)
                {
                    at += t[i];
                }
                else
                {
                    hold--;
                }
            }
        }
        return as == at;
    }
};