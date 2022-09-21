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
            actual_output = obj->countSubstrings(line);

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