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
    vector<int> nums;
    string s;
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
            string x = RemoveAllPunctInArray(line);
            stringstream ss(x);
            string v;
            vector<string> nums0;
            while (ss >> v)
            {
                nums0.push_back(v);
            }
            s = nums0[0];
            for (int i = 1; i < nums0.size(); i++)
            {
                nums.push_back(stoi(nums0[i]));
            }
            nums0.clear();
        }
        else
        {
            actual_output = obj->shiftingLetters(s, nums);
            RemoveDoubleQuotes(line);
            expected_output = line;
            if (checkOuputString(expected_output, actual_output, input1))
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