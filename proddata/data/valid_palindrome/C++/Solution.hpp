#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    bool isPalindrome(string s)
    {
        int n = s.size();
        int st = 0;
        int e = n - 1;
        if (n == 1)
        {
            return 1;
        }
        while (st < e)
        {
            while (st < e && !isalnum(s[st]))
            {
                st++;
            }
            while (st < e && !isalnum(s[e]))
            {
                e--;
            }
            if (st < e && tolower(s[st]) != tolower(s[e]))
            {
                return false;
            }
            st++;
            e--;
        }
        return true;
    }
};
