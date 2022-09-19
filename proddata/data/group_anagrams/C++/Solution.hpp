#include <vector>
#include <queue>
#include <set>
#include <unordered_map>
#include <string>
#include <algorithm>
#include <map>
using namespace std;

class Solution
{
public:
    vector<vector<string>> groupAnagrams(vector<string> &strs)
    {
        unordered_map<int, vector<string>> ans_m;
        for (string &str : strs)
        {
            unsigned long long strSum = 0;
            for (int i = 0; i < str.length(); i++)
            {
                strSum += (str[i] * str[i] * str[i] * str[i]);
            }

            ans_m[strSum].push_back(str);
        }
        vector<vector<string>> answer;
        for (pair<int, vector<string>> pair : ans_m)
            answer.emplace_back(move(pair.second));
        return answer;
    }
};