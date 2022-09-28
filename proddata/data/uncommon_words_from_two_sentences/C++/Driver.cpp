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
    vector<string> expected_output;
    vector<string> actual_output;
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
            actual_output = obj->uncommonFromSentences(s1, s2);
            RemoveAllPunctInStringArray(line);
            stringstream ss(line);
            string v;
            while (ss >> v)
            {
                expected_output.push_back(v);
            }
            sort(expected_output.begin(), expected_output.end());
            sort(actual_output.begin(), actual_output.end());
            if (checkOuputStringVec(expected_output, actual_output, input1))
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