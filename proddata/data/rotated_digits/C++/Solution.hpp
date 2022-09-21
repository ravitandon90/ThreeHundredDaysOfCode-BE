#include <iostream>
using namespace std;

class Solution
{
public:
    bool isGood(string curr)
    {

        bool flag = false;
        string temp = curr;
        for (int i = 0; i < curr.length(); i++)
        {
            if (curr[i] == '3' || curr[i] == '7' || curr[i] == '4')
                return false;
            else if (curr[i] == '2' || curr[i] == '6' || curr[i] == '5' || curr[i] == '9')
                flag = true;
        }
        return flag;
    }

    int rotatedDigits(int n)
    {

        int res = 0;

        for (int i = 1; i <= n; i++)
        {
            string curr = to_string(i);
            if (isGood(curr))
                res++;
        }
        return res;
    }
};