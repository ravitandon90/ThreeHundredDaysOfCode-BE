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

int convert(int n)
{
    long long dec = 0, i = 0, rem;

    while (n != 0)
    {
        rem = n % 10;
        n /= 10;
        dec += rem * pow(2, i);
        ++i;
    }
    return dec;
}

int main()
{
    int numInputs = 0;
    int target = 0;
    uint32_t input;
    int expected_output;
    int actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int lineNo = 1;
    while (getline(infile, line))
    {
        if (lineNo == 1)
        {
            stringstream ss(line);
            ss >> input;
            if (input < 2147483648)
            {
                actual_output = obj->hammingWeight(convert(input));
            }
            else
            {
                actual_output = obj->hammingWeight(input - 2);
            }
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