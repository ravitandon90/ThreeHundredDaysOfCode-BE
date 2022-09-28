#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <queue>
// #include <bits/stdc++.h>

using namespace std;

class Solution
{
public:
    string getLine(queue<string> &q, int maxWidth, bool isLastLine, int sLen)
    {
        string s = "";
        if (isLastLine)
        {
            while (!q.empty())
            {
                s += q.front();
                q.pop();
                s += " ";
            }
            if (s.length() == maxWidth + 1)
                return s.substr(0, maxWidth);
            while (s.length() < maxWidth)
            {
                s += " ";
            }
            return s.substr(0, maxWidth);
        }
        else if (q.size() <= 2)
        {
            s += q.front();
            q.pop();
            s += " ";

            int secondWordLength = 0;
            if (!q.empty())
                secondWordLength += q.front().size();

            while (s.length() + secondWordLength < maxWidth)
            {
                s += " ";
            }
            if (secondWordLength > 0)
            {
                s += q.front();
                q.pop();
            }
            return s.substr(0, maxWidth);
        }
        int noOfWords = q.size();

        int maxSpace = maxWidth - sLen + noOfWords;

        int extraSpace = maxSpace % (noOfWords - 1);
        int space = maxSpace / (noOfWords - 1);
        while (!q.empty())
        {
            s += q.front();
            q.pop();
            int t = space;
            if (extraSpace > 0)
            {
                t++;
                extraSpace--;
            }
            while (t--)
            {
                s += " ";
            }
        }
        return s.substr(0, maxWidth);
    }

    vector<string> fullJustify(vector<string> &words, int maxWidth)
    {
        vector<string> result;

        queue<string> q;

        int sLen = 0;

        for (int i = 0; i < words.size(); i++)
        {
            if (sLen + words[i].size() > maxWidth)
            {
                result.push_back(getLine(q, maxWidth, false, sLen));
                sLen = 0;
            }
            if (i == words.size() - 1)
            {
                q.push(words[i]);
                sLen += words[i].size() + 1;
                result.push_back(getLine(q, maxWidth, true, sLen));
            }
            else
            {
                q.push(words[i]);
                sLen += words[i].size() + 1;
            }
        }
        return result;
    }
};