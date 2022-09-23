#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

class Solution
{
public:
    void reverseString(vector<char> &s)
    {
        int si = s.size();
        char temp;
        for (int i = 0; i < si / 2; i++)
        {
            temp = s[i];
            s[i] = s[si - (i + 1)];
            s[si - (i + 1)] = temp;
        }
    }
};