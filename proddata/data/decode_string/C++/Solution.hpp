#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    string decodeString(string s)
    {
        int i, loop, n = s.size(), j;
        stack<char> stk;
        string temp, temp1, ans;
        for (i = 0; i < n; i++)
        {
            if (s[i] != ']')
                stk.push(s[i]);
            else
            {
                temp = "";
                while (stk.top() != '[')
                {
                    temp += stk.top();
                    stk.pop();
                }
                stk.pop();
                temp1 = "";
                while (!stk.empty() && (stk.top() > 47 && stk.top() < 58))
                {
                    temp1 += stk.top();
                    stk.pop();
                }
                reverse(temp1.begin(), temp1.end());
                reverse(temp.begin(), temp.end());
                loop = stoi(temp1);
                while (loop--)
                {
                    for (j = 0; j < temp.size(); j++)
                        stk.push(temp[j]);
                }
            }
        }
        while (!stk.empty())
        {
            ans += stk.top();
            stk.pop();
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};