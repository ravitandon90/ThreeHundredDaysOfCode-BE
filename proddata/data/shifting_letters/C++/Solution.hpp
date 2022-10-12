#include <vector>
#include <numeric>
#include <map>
using namespace std;
class Solution
{
public:
    string shiftingLetters(string s, vector<int> &shifts)
    {
        int temp = 0;
        for (int i = s.size() - 1; i >= 0; i--)
        {
            temp += shifts[i];
            temp %= 26;
            if (temp > 'z' - s[i])
                s[i] += temp - 26;
            else
                s[i] += temp;
        }
        return s;
    }
};