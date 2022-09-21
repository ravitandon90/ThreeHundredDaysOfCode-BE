#include <iostream>
#include <string>
using namespace std;

class Solution
{
private:
    bool deleted = false;

public:
    bool validPalindrome(string s)
    {
        int left = 0, right = s.length() - 1;
        while (left < right)
        {
            if (s[left] != s[right])
            {
                if (deleted)
                    return false;
                deleted = true;
                return validPalindrome(s.substr(left + 1, right - left)) || validPalindrome(s.substr(left, right - left));
            }
            left += 1;
            right -= 1;
        }

        return true;
    }
};