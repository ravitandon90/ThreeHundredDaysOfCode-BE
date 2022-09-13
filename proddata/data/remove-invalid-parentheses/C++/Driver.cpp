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
    string s;
    vector<string> expected_output;
    vector<string> actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int lineNo = 0;
    while (getline(infile, line))
    {
        lineNo += 1;

        if (lineNo == 1)
        {
            s = line;
        }

        if (lineNo == 2)
        {
            stringstream ss(line);
            string n;
            if (line == " ")
            {
                expected_output.push_back({});
            }
            while (ss >> n)
            {
                expected_output.push_back(n);
            }

            actual_output = obj->removeInvalidParentheses(s);

            sort(actual_output.begin(), actual_output.end());
            sort(expected_output.begin(), expected_output.end());
            if (actual_output != expected_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Actual Output: ";
                for (int i = 0; i < actual_output.size(); i++)
                {
                    cout << actual_output[i] << " ";
                }
                cout << endl;
                cout << "Expected Output: ";
                for (int i = 0; i < expected_output.size(); i++)
                {
                    cout << expected_output[i] << " ";
                }
                cout << endl;
                return 0;
            }
            expected_output.clear();
            actual_output.clear();

            lineNo = 0;
        }
    }
    cout << "Result: Success" << endl;
    return 0;
}
