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
    string input1;
    int k;
    int v;
    vector<int> nums;
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
            line = RemoveAllPunctInArray(line);
            stringstream ss(line);
            while (ss >> v)
            {
                nums.push_back(v);
            }
            k = nums.back();
            nums.pop_back();
        }
        else
        {
            vector<bool> res = obj->kidsWithCandies(nums, k);
            for (auto x : res)
            {
                actual_output.push_back(convertInttoBool(x));
            }
            string x = RemoveAllPunctInArray(line);
            stringstream ss(x);
            string vv;
            while (ss >> vv)
            {
                expected_output.push_back(vv);
            }
            if (checkOuputStringVec(expected_output, actual_output, input1))
            {
                return 0;
            }
            nums.clear();
            expected_output.clear();
            actual_output.clear();
        }
        status += 1;
    }
    Success();
    return 0;
}