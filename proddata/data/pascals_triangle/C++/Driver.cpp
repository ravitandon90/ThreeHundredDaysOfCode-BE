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

void Print(vector<vector<int>> actual_output, vector<vector<int>> expected_output)
{
    cout << "Result: Failed" << endl;
    cout << "Actual Output:" << endl;
    for (int i = 0; i < actual_output.size(); i++)
    {
        for (int j = 0; j < actual_output[i].size(); j++)
        {
            cout << actual_output[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;

    cout << "Expected Output:" << endl;
    for (int i = 0; i < expected_output.size(); i++)
    {
        for (int j = 0; j < expected_output[i].size(); j++)
        {
            cout << expected_output[i][j] << " ";
        }
        cout << endl;
    }
}

int main()
{
    int input;
    vector<vector<int>> expected_output;
    vector<vector<int>> actual_output;
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
            actual_output = obj->generate(input);

            sort(actual_output.begin(), actual_output.end());

            sort(expected_output.begin(), expected_output.end());

            if (expected_output != actual_output)
            {
                Print(actual_output, expected_output);
                return 0;
            }
            expected_output.clear();
        }

        if (status == 1 && line != "check")
        {
            istringstream ss(line);
            ss >> input;
        }
        if (status == 0 && line != "check")
        {
            stringstream ss(line);
            vector<int> new_vec;
            int v;
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