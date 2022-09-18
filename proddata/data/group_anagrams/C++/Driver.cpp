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

void Print(vector<vector<string>> actual_output, vector<vector<string>> expected_output, vector<string> nums)
{
    cout << "Result: Failed" << endl;
    cout << "Input: ";
    cout << "strs= ";
    for (auto x : nums)
    {
        cout << x << " ";
    }
    cout << "\nExpected Output:" << endl;
    for (int i = 0; i < expected_output.size(); i++)
    {
        for (int j = 0; j < expected_output[i].size(); j++)
        {
            cout << expected_output[i][j] << " ";
        }
        cout << endl;
    }

    cout << "Actual Output:" << endl;
    for (int i = 0; i < actual_output.size(); i++)
    {
        for (int j = 0; j < actual_output[i].size(); j++)
        {
            cout << actual_output[i][j] << " ";
        }
        cout << endl;
    }
}

int main()
{
    int vectorSize;
    vector<string> nums;
    vector<vector<string>> expected_output;
    vector<vector<string>> actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int status = 0;
    while (getline(infile, line))
    {
        if (line == "input")
        {
            status = 1;
            continue;
        }

        if (line == "output")
        {
            status = 0;
            continue;
        }

        if (line == "check")
        {
            vector<vector<string>> res = obj->groupAnagrams(nums);
            for (auto y : res)
            {
                sort(y.begin(), y.end());
                actual_output.push_back(y);
            }
            sort(actual_output.begin(), actual_output.end());

            sort(expected_output.begin(), expected_output.end());

            if (expected_output != actual_output)
            {
                Print(actual_output, expected_output, nums);
                return 0;
            }
            nums.clear();
            expected_output.clear();
            actual_output.clear();
        }

        if (status == 1 && line != "check")
        {
            istringstream ss(line);
            string v;
            while (ss >> v)
            {
                nums.push_back(v);
            }
        }
        if (status == 0 && line != "check")
        {
            stringstream ss(line);
            vector<string> new_vec;
            string v;
            while (ss >> v)
            {
                new_vec.push_back(v);
            }
            expected_output.push_back(new_vec);
            new_vec.clear();
        }
    }
    cout << "Result: Success" << endl;
    return 0;
}