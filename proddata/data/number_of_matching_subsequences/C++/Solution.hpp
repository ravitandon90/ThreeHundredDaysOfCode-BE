#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    typedef pair<int, int> PII;

    int numMatchingSubseq(string s, vector<string> &words)
    {
        const int n = words.size();
        vector<PII> letters[26];

        for (int i = 0; i < words.size(); i++)
        {
            char ch = words[i][0];
            letters[ch - 'a'].push_back({i, 0});
        }

        int res = 0;
        for (int j = 0; j < s.size(); j++)
        {
            char ch = s[j];

            auto buf = letters[ch - 'a'];
            letters[ch - 'a'].clear();
            while (buf.size())
            {
                auto t = buf.back();
                buf.pop_back();

                if (t.second + 1 == words[t.first].size())
                {
                    res++;
                }
                else
                {
                    char c = words[t.first][t.second + 1];
                    letters[c - 'a'].push_back({t.first, t.second + 1});
                }
            }
        }

        return res;
    }
};