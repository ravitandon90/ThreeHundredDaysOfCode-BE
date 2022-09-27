#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    bool isAnagram(string s, string t)
    {
        vector<int> s1(26, 0);
        vector<int> s2(26, 0);
        for (char c : s)
        {
            s1[c - 'a']++;
        }
        for (char c : t)
        {
            s2[c - 'a']++;
        }
        if (s1 == s2)
            return true;
        return false;
    }
};