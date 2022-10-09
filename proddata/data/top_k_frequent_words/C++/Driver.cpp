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
#include "../../cpp/helper.hpp"

using namespace std;

int main()
{
    string input1;
    int k;
    string v;
    vector<string> nums;
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
            RemoveAllPunctInStringArray(line);
            stringstream ss(line);
            while (ss >> v)
            {
                nums.push_back(v);
            }
            stringstream s(nums.back());
            s >> k;
            nums.pop_back();
        }
        else
        {
            actual_output = obj->topKFrequent(nums, k);
            string x = RemoveAllPunctInArray(line);
            stringstream ss(x);
            while (ss >> v)
            {
                expected_output.push_back(v);
            }
            if (checkOuputStringVec(expected_output, actual_output, input1))
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