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
    vector<string> input;
    int expected_output;
    int actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int lineNo = 0;
    while (getline(infile, line))
    {
        if (lineNo == 0)
        {
            lineNo += 1;
            continue;
        }
        if ((lineNo % 2) != 0)
        {
            string s = line;
            stringstream ss(s);
            istream_iterator<string> begin(ss);
            istream_iterator<string> end;
            vector<string> input(begin, end);
            actual_output = obj->countSubstrings(input[0], input[1]);
        }
        else
        {
            stringstream ss(line);
            ss >> expected_output;
            if (expected_output != actual_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Actual Output:" << actual_output << endl;
                cout << "Expected Output:" << expected_output << endl;
                return 0;
            }
        }
        lineNo += 1;
    }
    cout << "Result: Success" << endl;
    return 0;
}