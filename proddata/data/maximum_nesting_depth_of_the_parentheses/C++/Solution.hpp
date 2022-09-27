#include <iostream>
#include <string>
#include <set>
using namespace std;

class Solution
{
public:
    int maxDepth(string s)
    {
        stack<char> a;

        for (int i = 0; i < s.size(); i++)
            if (s[i] == '(' || s[i] == ')')
                a.push(s[i]);

        int m = 0;
        int count = 0;
        while (!a.empty())
        {
            if (a.top() == ')')
                count++;
            else if (a.top() == '(')
                count--;
            if (count > m)
                m = count;
            a.pop();
        }

        return m;
    }
};