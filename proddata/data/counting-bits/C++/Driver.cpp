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
    vector<int> expected_output;
    vector<int> actual_output;
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
            int n;
            while (ss >> n)
            {
                expected_output.push_back(n);
            }

            actual_output = obj->countBits(s);

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
