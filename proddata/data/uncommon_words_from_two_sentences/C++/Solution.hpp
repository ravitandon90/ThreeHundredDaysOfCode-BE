#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;
class Solution
{
public:
    vector<string> uncommonFromSentences(string s1, string s2)
    {
        unordered_map<string, int> w1;
        int c = 0, j = 0;
        string a;
        for (char i = s1[j]; i != '\0'; j++, i = s1[j])
        {
            if (i == ' ' || i == '\0')
            {
                w1[a]++;
                a = "";
            }
            else
                a += i;
        }
        w1[a]++;
        a = "";
        j = 0;
        for (char i = s2[j]; i != '\0'; j++, i = s2[j])
        {
            if (i == ' ' || i == '\0')
            {
                w1[a]++;
                a = "";
            }
            else
                a += i;
        }
        w1[a]++;

        vector<string> d;
        for (auto i : w1)
            if (i.second == 1)
                d.push_back(i.first);
        return d;
    }
};