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
#include <bits/stdc++.h>

#include "Solution.hpp"
#include "../../cpp/helper.hpp"

using namespace std;

int main()
{
    string input1;
    vector<vector<int>> nums;
    int n = 0;
    double expected_output;
    double actual_output;
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
            parseIntVectorOfVectorAndInt(line, nums, n);
        }
        else
        {
            actual_output = obj->maxAverageRatio(nums, n);
            stringstream ss(line);
            ss >> expected_output;
            if (checkOuputDouble(expected_output, actual_output, input1))
            {
                return 0;
            }
            nums.clear();
        }
        status += 1;
    }
    Success();
    return 0;
}