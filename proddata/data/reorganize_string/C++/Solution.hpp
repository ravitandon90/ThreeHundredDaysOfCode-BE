#include <iostream>
#include <string>
#include <queue>
#include <unordered_map>
using namespace std;

class Solution
{
public:
    string reorganizeString(string s)
    {
        int N = s.size();
        unordered_map<char, int> mHash;

        for (auto &c : s)
        {
            ++mHash[c];
            if (mHash[c] > (N + 1) / 2)
                return "";
        }

        auto comp_lbd = [](pair<char, int> a, pair<char, int> b)
        {
            return a.second < b.second;
        };

        priority_queue<pair<char, int>, vector<pair<char, int>>, decltype(comp_lbd)> mPQ(comp_lbd);
        for (auto &p : mHash)
        {
            mPQ.push(p);
        }
        string ans = "";
        while (!mPQ.empty() && ans.size() < s.size())
        {
            auto p = mPQ.top();
            mPQ.pop();
            while (p.second > 0)
            {
                ans.push_back(p.first);
                --p.second;
                if (mPQ.empty())
                    break;
                ans.push_back(mPQ.top().first);
                auto p1 = mPQ.top();
                mPQ.pop();
                --p1.second;
                if (p1.second > 0)
                {
                    mPQ.push(p1);
                }
            }
        }
        return ans;
    }
};