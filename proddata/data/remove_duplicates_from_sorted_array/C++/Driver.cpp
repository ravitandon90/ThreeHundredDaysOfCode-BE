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
    int expected_output;
    int actual_output;
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
            int v;
            while (ss >> v)
            {
                nums.push_back(v);
            }
        }
        else
        {
            actual_output = obj->removeDuplicates(nums);
            stringstream ss(line);
            ss >> expected_output;
            if (checkOuputInt(expected_output, actual_output, input1))
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