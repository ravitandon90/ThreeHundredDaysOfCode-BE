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
    vector<int> nums;
    string expected_output;
    string actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int status = 0;
    while (getline(infile, line))
    {
        if (status % 2 == 0)
        {
            stringstream ss(line);
            int v;
            while (ss >> v)
            {
                nums.push_back(v);
            }
        }
        if (status % 2 == 1)
        {
            bool res = obj->canPartition(nums);
            if (res)
            {
                actual_output = "true";
            }
            else
            {
                actual_output = "false";
            }
            expected_output = line;
            if (expected_output != actual_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Actual Output: " << actual_output << endl;
                cout << "Expected Output: " << expected_output << endl;
                return 0;
            }
            nums.clear();
        }
        status += 1;
    }
    cout << "Result: Success" << endl;
    return 0;
}