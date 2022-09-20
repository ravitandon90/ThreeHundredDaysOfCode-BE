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

#include "Solution.hpp"

using namespace std;

int main()
{
    int numInputs = 0;
    int target = 0;
    string input1;
    string expected_output;
    string actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int lineNo = 0;
    while (getline(infile, line))
    {
        line.erase(remove(line.begin(), line.end(), '\"'), line.end());

        if (lineNo == 0)
        {
            lineNo += 1;
            continue;
        }
        if ((lineNo % 2) != 0)
        {
            input1 = line;
            string s = line;
            string res = obj->toGoatLatin(s);
            actual_output = res;
        }
        else
        {
            expected_output = line;

            if (expected_output != actual_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Input: " << input1 << endl;
                cout << "Expected Output: " << expected_output << endl;
                cout << "Actual Output: " << actual_output << endl;
                return 0;
            }
        }
        lineNo += 1;
    }
    cout << "Result: Success" << endl;
    return 0;
}