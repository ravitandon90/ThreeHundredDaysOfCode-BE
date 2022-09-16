#include <iostream>
using namespace std;

class Solution
{
public:
    int climbStairs(int n)
    {
        if (n < 2)
            return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
};