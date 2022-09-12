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
    int s;
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
            stringstream ss(line);
            ss >> s;
        }

        if (lineNo == 2)
        {
            istringstream ss(line);
            string n;
            while (ss >> n)
            {
                expected_output.push_back(n);
            }

            // cout << "Expected output" << endl;
            // for (int i = 0; i < expected_output.size(); i++)
            // {
            //     cout << expected_output[i] << endl;
            // }

            actual_output = obj->generateParenthesis(s);

            // cout << "actual_output" << endl;
            // for (int j = 0; j < actual_output.size(); j++)
            // {
            //     cout << actual_output[j] << endl;
            // }

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
