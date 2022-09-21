#include <iostream>
#include <string>
#include <queue>
using namespace std;

class Solution
{
public:
    string longestDiverseString(int a, int b, int c)
    {
        priority_queue<pair<int, int>> q;
        if (a > 0)
        {
            q.push({a,
                    'a'});
        }
        if (b > 0)
        {
            q.push({b,
                    'b'});
        }
        if (c > 0)
        {
            q.push({c,
                    'c'});
        }
        string ans = "";
        while (q.size() > 1)
        {
            auto t1 = q.top();
            q.pop();
            auto t2 = q.top();
            q.pop();
            if (t1.first >= 2)
            {
                if ((ans.length() == 0) || (ans.length() > 1 && ans[ans.length() - 1] != t1.second))
                {
                    ans += t1.second;
                    ans += t1.second;
                    t1.first -= 2;
                }
                else
                {
                    ans += t1.second;
                    t1.first -= 1;
                }
            }
            else
            {
                ans += t1.second;
                t1.first -= 1;
            }
            ans += t2.second;
            if (t2.first - 1 > 0)
            {
                q.push({t2.first - 1,
                        t2.second});
            }
            if (t1.first > 0)
            {
                q.push({t1.first,
                        t1.second});
            }
        }
        if (!q.empty())
        {
            auto temp = q.top();
            q.pop();
            if (ans.length() == 0 || temp.second != ans[ans.length() - 1])
            {
                if (temp.first >= 2)
                {
                    ans += temp.second;
                    ans += temp.second;
                }
                else
                {
                    ans += temp.second;
                }
            }
            else if (ans.length() == 0 || ans[ans.length() - 2] != ans[ans.length() - 1])
            {
                ans += temp.second;
            }
        }
        return ans;
    }
};