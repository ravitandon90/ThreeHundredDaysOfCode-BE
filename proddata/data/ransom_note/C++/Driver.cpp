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

#include "../../cpp/helper1.hpp"

using namespace std;

int main()
{
    int numInputs = 0;
    int target = 0;
    string input1;
    vector<int> input;
    string expected_output;
    string actual_output;
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
            input1 = line;
            RemoveAllPunctInString(line);
            stringstream ss(line);
            istream_iterator<string> begin(ss);
            istream_iterator<string> end;
            vector<string> input(begin, end);
            bool res = obj->canConstruct(input[0], input[1]);
            actual_output = convertInttoBool(res);
        }
        else
        {
            expected_output = line;

            if (checkOuputString(expected_output, actual_output, input1))
            {
                return 0;
            }
        }
        lineNo += 1;
    }
    Success();
    return 0;
}