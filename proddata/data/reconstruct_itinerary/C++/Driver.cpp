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

void Print(vector<string> actual_output, vector<string> expected_output, vector<vector<string>> input)
{
    cout << "Result: Failed" << endl;
    cout << "Input: tickets=\n";
    for (int i = 0; i < input.size(); i++)
    {
        for (int j = 0; j < input[i].size(); j++)
        {
            cout << input[i][j] << " ";
        }
        cout << endl;
    }
    cout << "Expected Output:" << endl;
    for (int i = 0; i < expected_output.size(); i++)
    {
        cout << expected_output[i] << " ";
    }
    cout << "\nActual Output:" << endl;
    for (int i = 0; i < actual_output.size(); i++)
    {
        cout << actual_output[i] << " ";
    }
    cout << endl;
}

int main()
{
    int vectorSize;
    vector<vector<string>> input;
    vector<string> expected_output;
    vector<string> actual_output;
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
            actual_output = obj->findItinerary(input);

            sort(actual_output.begin(), actual_output.end());

            // for (int i = 0; i < actual_output.size(); i++)
            // {
            //     for (int j = 0; j < actual_output[i].size(); j++)
            //     {
            //         cout << actual_output[i][j] << endl;
            //     }
            // }

            sort(expected_output.begin(), expected_output.end());

            // for (int i = 0; i < expected_output.size(); i++)
            // {
            //     for (int j = 0; j < expected_output[i].size(); j++)
            //     {
            //         cout << expected_output[i][j] << endl;
            //     }
            // }
            if (expected_output != actual_output)
            {
                Print(actual_output, expected_output, input);
                return 0;
            }

            // if (expected_output.size() != actual_output.size())
            // {
            //     Print(actual_output, expected_output);
            //     return 0;
            // }
            // if (expected_output.size() == actual_output.size())
            // {
            //     for (int i = 0; i < expected_output.size(); i++)
            //     {
            //         if (expected_output[i].size() != actual_output[i].size())
            //         {
            //             Print(actual_output, expected_output);
            //             return 0;
            //         }
            //     }
            // }

            input.clear();
            expected_output.clear();
        }

        if (status == 1 && line != "check")
        {
            stringstream ss(line);
            vector<string> new_vec;
            string v;
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
            string v;
            while (ss >> v)
            {
                expected_output.push_back(v);
            }
        }
    }
    cout << "Result: Success" << endl;
    return 0;
}