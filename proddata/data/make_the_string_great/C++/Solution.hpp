#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    string makeGood(string s)
    {
        string res = "";
        for (char c : s)
        {
            if (res.size() == 0 || res.back() == c)
                res.push_back(c);
            else
            {
                if (toupper(res.back()) == toupper(c))
                    res.pop_back();
                else
                    res.push_back(c);
            }
        }
        return res;
    }
};