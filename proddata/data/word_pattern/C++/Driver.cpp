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
#include <regex>

#include "Solution.hpp"
#include "../../cpp/helper.hpp"

using namespace std;

int main()
{
    string input1;
    string s1, s2;
    string expected_output;
    string actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int status = 0;
    while (getline(infile, line))
    {
        if (status == 0)
        {
            status += 1;
            continue;
        }
        if (status % 2 != 0)
        {
            input1 = line;
            parseTwoStrings(line, s1, s2);
        }
        else
        {
            bool res = obj->wordPattern(s1, s2);
            actual_output = convertInttoBool(res);
            expected_output = line;
            if (checkOuputString(expected_output, actual_output, input1))
            {
                return 0;
            }
            expected_output.clear();
        }
        status += 1;
    }
    Success();
    return 0;
}