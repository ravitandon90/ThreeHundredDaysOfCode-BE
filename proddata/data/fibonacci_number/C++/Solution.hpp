#include <iostream>
using namespace std;

class Solution
{
public:
    int fib(int n)
    {
        vector<int> memo(n + 2, 0);
        memo[1] = 1;

        if (n < 2)
            return n;

        for (int i = 2; i <= n; i++)
        {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }
};