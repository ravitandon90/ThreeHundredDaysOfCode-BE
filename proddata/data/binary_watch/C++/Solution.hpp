#include <vector>
#include <string>
#include <algorithm>
using namespace std;
class Solution
{
public:
    int cntOnes(int n)
    {
        int res = 0;
        while (n)
        {
            res += n & 1;
            n = n >> 1;
        }
        return res;
    }
    vector<string> readBinaryWatch(int turnedOn)
    {
        int hours = 0, minutes = 0;
        vector<string> res;
        while (hours != 12)
        {
            int cnt = cntOnes(hours) + cntOnes(minutes);
            if (cnt == turnedOn)
            {
                string minutesStr = "";
                if (minutes < 10)
                    minutesStr = "0";
                res.push_back(to_string(hours) + ":" + minutesStr + to_string(minutes));
            }
            minutes++;
            if (minutes == 60)
            {
                hours++;
                minutes = 0;
            }
        }
        return res;
    }
};