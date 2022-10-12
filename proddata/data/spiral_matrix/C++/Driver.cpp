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
    vector<vector<int>> nums;
    int s = 0;
    vector<int> expected_output;
    vector<int> actual_output;
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
            parseVectorOfVector(line, nums);
        }
        else
        {
            actual_output = obj->spiralOrder(nums);
            line=RemoveAllPunctInArray(line);
            stringstream ss(line);
            while(ss >> s){
                expected_output.push_back(s);
            }
            if (checkOuputIntVec(expected_output, actual_output, input1))
            {
                return 0;
            }
            nums.clear();
            expected_output.clear();
        }
        status += 1;
    }
    Success();
    return 0;
}