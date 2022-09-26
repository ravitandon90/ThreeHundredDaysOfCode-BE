#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <queue>
using namespace std;

class Solution
{
public:
    class comp
    {
    public:
        bool operator()(pair<int, string> p1, pair<int, string> p2)
        {
            if (p1.first == p2.first)
            {

                return p1.second < p2.second;
            }
            else
                return p1.first > p2.first;
        }
    };

    vector<string> topKFrequent(vector<string> &words, int k)
    {
        vector<string> ans;
        stack<string> st;
        unordered_map<string, int> mp;

        for (string s : words)
        {
            mp[s]++;
        }
        priority_queue<pair<int, string>, vector<pair<int, string>>, comp> p;

        for (auto it : mp)
        {
            p.push({it.second,
                    it.first});
            if (p.size() > k)
            {
                p.pop();
            }
        }

        while (p.size() > 0)
        {
            st.push(p.top().second);
            p.pop();
        }
        while (st.size() > 0)
        {
            ans.push_back(st.top());
            st.pop();
        }
        return ans;
    }
};