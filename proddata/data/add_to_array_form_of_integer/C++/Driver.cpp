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
    int numInputs = 0;
    int target = 0;
    vector<int> num;
    int k = 0;
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
            istringstream ss(line);
            int v;
            while (ss >> v)
            {
                num.push_back(v);
            }
        }
        if (lineNo == 2)
        {
            stringstream s(line);
            s >> k;
        }

        if (lineNo == 3)
        {
            istringstream ss(line);
            int n;
            while (ss >> n)
            {
                expected_output.push_back(n);
            }

            actual_output = obj->addToArrayForm(num, k);

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

            num.clear();
            expected_output.clear();
            actual_output.clear();

            lineNo = 0;
        }
    }
    cout << "Result: Success" << endl;
    return 0;
}
