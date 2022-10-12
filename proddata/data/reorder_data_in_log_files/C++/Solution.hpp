#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;
class Solution
{
public:
    vector<string> reorderLogFiles(vector<string> &logs)
    {
        vector<vector<string>> letters;
        vector<string> digits;
        for (string &log : logs)
        {
            if (isdigit(log[log.length() - 1]))
            {
                digits.push_back(log);
            }
            else
            {
                auto content = log.substr(log.find(' ') + 1);
                auto identifier = log.substr(0, log.find(' '));
                letters.push_back({content,
                                   identifier});
            }
        }
        sort(letters.begin(), letters.end());
        vector<string> result;
        for (auto &letter : letters)
        {
            result.push_back(letter[1] + ' ' + letter[0]);
        }
        for (string &digit : digits)
        {
            result.push_back(digit);
        }
        return result;
    }
};