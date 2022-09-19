#include <iostream>
using namespace std;

class Solution
{
public:
    int reverse(int x)
    {
        long store = 0;
        while (x != 0)
        {

            int temp;
            temp = x % 10;
            store = store * 10 + temp;
            x = x / 10;
        }
        if (store < INT_MIN || store > INT_MAX)
        {
            return 0;
        }
        else
        {
            return store;
        }
    }
};