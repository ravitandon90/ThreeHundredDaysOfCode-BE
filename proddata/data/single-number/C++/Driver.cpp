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
    int expected_output;
    int actual_output;
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
            actual_output = obj->singleNumber(nums);
            stringstream ss(line);
            ss >> expected_output;
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