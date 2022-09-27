#include <bits/stdc++.h>
using namespace std;
class Solution
{
public:
    int findKthPositive(vector<int> &arr, int k)
    {
        int temp = k;
        for (int i = 0; i < arr.size(); i++)
        {
            if (arr[i] <= temp)
                temp++;
        }
        return temp;
    }
};
