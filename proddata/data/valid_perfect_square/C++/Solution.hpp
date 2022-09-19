#include <iostream>
using namespace std;

class Solution
{
public:
    bool isPerfectSquare(int num)
    {
        int st = 1, mid, end = num / 2 + 1;
        while (st <= end)
        {
            mid = st + (end - st) / 2;
            if (mid == (double)num / mid)
                return true;
            else if (mid > num / mid)
                end = mid - 1;
            else
                st = mid + 1;
        }
        return false;
    }
};