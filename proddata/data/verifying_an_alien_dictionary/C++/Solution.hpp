#include <iostream>
#include <string>
using namespace std;

#include <bits/stdc++.h>
class Solution
{
public:
    bool isAlienSorted(vector<string> &words, string order)
    {
        unordered_map<char, int> um;
        for (int i = 0; i < order.size(); i++)
        {
            um[order[i]] = i;
        }
        bool ans = false;

        for (int i = 1; i < words.size(); i++)
        {
            string w1 = words[i - 1];
            string w2 = words[i];
            for (int j = 0; j < w1.size(); j++)
            {
                if (w1[j] == w2[j])
                {
                    ans = true;
                    continue;
                }
                else if (um[w1[j]] < um[w2[j]])
                {
                    ans = true;
                    break;
                }
                else
                {
                    return false;
                }
            }
        }
        return ans;
    }
};