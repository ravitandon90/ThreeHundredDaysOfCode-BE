#include <iostream>
using namespace std;

class Solution
{
public:
    int countVowelStrings(int n)
    {
        int a = 1, e = 1, i = 1, o = 1, u = 1;
        int iteration = 1;
        while (++iteration <= n)
        {
            e = (a + e);
            i = (e + i);
            o = (i + o);
            u = (o + u);
        }
        return a + e + i + o + u;
    }
};