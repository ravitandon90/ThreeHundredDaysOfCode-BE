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

void Print(int actual_output, int expected_output, vector<vector<int>> input)
{
    cout << "Result: Failed" << endl;
    cout << "Input:" << endl;
    for (int i = 0; i < input.size(); i++)
    {
        for (int j = 0; j < input[i].size(); j++)
        {
            cout << input[i][j] << " ";
        }
        cout << endl;
    }
    cout << "Expected Output:" << expected_output << endl;
    cout << "Actual Output:" << actual_output << endl;
}

int main()
{
    int vectorSize;
    vector<vector<int>> input;
    int expected_output;
    int actual_output;
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
            actual_output = obj->islandPerimeter(input);

            if (expected_output != actual_output)
            {
                Print(actual_output, expected_output, input);
                return 0;
            }

            input.clear();
        }

        if (status == 1 && line != "check")
        {
            stringstream ss(line);
            vector<int> new_vec;
            int v;
            while (ss >> v)
            {
                new_vec.push_back(v);
            }
            input.push_back(new_vec);
            new_vec.clear();
        }
        if (status == 0 && line != "check")
        {
            stringstream ss(line);
            ss >> expected_output;
        }
    }
    cout << "Result: Success" << endl;
    return 0;
}