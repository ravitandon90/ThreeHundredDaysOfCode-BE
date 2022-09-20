#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    int strStr(string haystack, string needle)
    {

        size_t i;

        i = haystack.find(needle);

        if (i == string ::npos)
            return -1;
        else
            return i;
    }
};