#include <algorithm>
#include <fstream>
#include <iostream>
#include <stack>
#include <string>
#include <vector>
#include <sstream>
#include <iterator>
#include <unordered_map>
#include <unordered_set>
#include <math.h>

#include "Solution.hpp"

using namespace std;

int binaryToDecimal(string n)
{
    string num = n;
    int dec_value = 0;

    int base = 1;

    int len = num.length();
    for (int i = len - 1; i >= 0; i--)
    {
        if (num[i] == '1')
            dec_value += base;
        base = base * 2;
    }

    return dec_value;
}

int main()
{
    long long expected_output;
    long long actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int lineNo = 1;
    while (getline(infile, line))
    {
        if (lineNo == 1)
        {
            stringstream ss(line);

            actual_output = obj->reverseBits(binaryToDecimal(line));

            lineNo = 0;
        }
        else
        {
            lineNo = 1;
            stringstream x(line);
            x >> expected_output;
            if (expected_output != actual_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Actual Output:" << actual_output << endl;
                cout << "Expected Output:" << expected_output << endl;
                return 0;
            }
        }
    }
    cout << "Result: Success";
    return 0;
}
