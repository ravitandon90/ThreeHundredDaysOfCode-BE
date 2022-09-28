#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    int firstUniqChar(string s)
    {
        unordered_map<char, int> ump;
        unordered_map<char, int>::iterator itr;

        for (int i = 0; i < s.length(); i++)
            ump[s[i]]++;

        for (int i = 0; i < s.length(); i++)
        {
            if (ump[s[i]] == 1)
            {
                return i;
                break;
            }
        }

        return -1;
    }
};