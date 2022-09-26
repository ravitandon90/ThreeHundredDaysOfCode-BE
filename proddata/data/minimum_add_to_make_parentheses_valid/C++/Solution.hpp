#include <iostream>
#include <string>
#include <set>
using namespace std;

class Solution
{
public:
    int minAddToMakeValid(string &s)
    {
        stack<char> st;
        int ans = 0;
        for (auto &i : s)
        {
            if (i == ')' && st.empty())
            {
                ans++;
            }
            else if (i == ')' && !st.empty())
            {
                st.pop();
            }
            if (i == '(')
            {
                st.push(i);
            }
        }
        while (!st.empty())
        {
            st.pop();
            ans++;
        }
        return ans;
    }
};