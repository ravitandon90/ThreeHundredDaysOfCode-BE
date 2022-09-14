// https://leetcode.com/problems/add-strings/

#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

class Solution
{
public:
    string addStrings(string num1, string num2)
    {
        string res;
        int i = num1.size() - 1, j = num2.size() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0)
        {
            int sum = carry;
            if (i >= 0)
            {
                sum += num1[i] - '0';
                i--;
            }
            if (j >= 0)
            {
                sum += num2[j] - '0';
                j--;
            }
            carry = sum > 9 ? 1 : 0;
            res += to_string(sum % 10);
        }
        if (carry)
        {
            res += to_string(carry);
        }
        reverse(res.begin(), res.end());
        return res;
    }
};