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
    string input;
    int expected_output;
    int actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int lineNo = 0;
    while (getline(infile, line))
    {
        if (lineNo % 2 == 0)
        {
            actual_output = obj->romanToInt(line);
            input = line;
        }
        else
        {
            stringstream x(line);
            x >> expected_output;
            if (expected_output != actual_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Input: ";
                cout << "s= " << input;
                cout << "\nExpected Output: " << expected_output << endl;
                cout << "Actual Output: " << actual_output << endl;
                return 0;
            }
        }
        lineNo += 1;
    }
    cout << "Result: Success";
    return 0;
}