#include <vector>
#include <numeric>
#include <algorithm>
using namespace std;
class Solution
{
public:
    string convertToTitle(int columnNumber)
    {
        string res;
        char c;

        while (columnNumber > 0)
        {
            c = 'A' + (columnNumber - 1) % 26;
            res = c + res;
            columnNumber = (columnNumber - 1) / 26;
        }
        return res;
    }
};