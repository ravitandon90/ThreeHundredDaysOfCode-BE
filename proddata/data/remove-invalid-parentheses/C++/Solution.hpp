#include <vector>
#include <set>
#include <unordered_map>
#include <string>
#include <algorithm>
using namespace std;

#define vec vector
#define uset unordered_set
#define umap unordered_map
#define all(x) x.begin(), x.end()

class Solution
{
  umap<string, uset<string>> dp;

  uset<string> &solve(string s)
  {
    if (!dp[s].empty())
    {
      return dp[s];
    }

    deque<int> open, close;
    int n = s.size();
    for (int i = 0; i < n; i++)
    {
      if (s[i] == '(')
      {
        open.push_back(i);
      }
      else if (s[i] == ')')
      {
        if (open.empty())
        {
          close.push_back(i);
        }
        else
        {
          open.pop_back();
        }
      }
    }

    if (!open.empty() || !close.empty())
    {
      auto &ans = dp[s];
      for (int i = 0; i < n; i++)
      {
        if (s[i] == '(' && !open.empty() && i >= open.front())
        {
          ans.insert(all(solve(s.substr(0, i) + s.substr(i + 1))));
        }
        if (s[i] == ')' && !close.empty() && i <= close.back())
        {
          ans.insert(all(solve(s.substr(0, i) + s.substr(i + 1))));
        }
      }
      return ans;
    }
    else
    {
      return dp[s] = {s};
    }
  }

public:
  vector<string> removeInvalidParentheses(string s) { return {all(solve(s))}; }
};