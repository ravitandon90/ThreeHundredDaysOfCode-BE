#include <vector>
#include <string>
#include <algorithm>
using namespace std;
class Solution
{
public:
    void gen(string s, vector<string> &arr, int n, int l, int r)
    {
        if (l == n && r == n)
            arr.push_back(s);
        if (l < n)
        {
            s.append(1, '(');
            l++;
            gen(s, arr, n, l, r);
            l--;
            s.pop_back();
        }
        if (r < l)
        {
            s.append(1, ')');
            r++;
            gen(s, arr, n, l, r);
            r--;
            s.pop_back();
        }
    }
    vector<string> generateParenthesis(int n)
    {
        vector<string> arr;
        string s;
        gen(s, arr, n, 0, 0);
        return arr;
    }
};