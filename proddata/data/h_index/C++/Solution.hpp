#include <bits/stdc++.h>
using namespace std;
class Solution
{
public:
    int hIndex(vector<int> &citations)
    {
        int ans = 0;
        int n = citations.size();

        sort(citations.begin(), citations.end());
        for (int i = n - 1; i >= 0; i--)
        {
            if (citations[i] >= (n - i))
                ans++;
        }
        return ans;
    }
};
