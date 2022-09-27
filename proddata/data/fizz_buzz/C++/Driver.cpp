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

#include "../../cpp/helper1.hpp"

using namespace std;

int main()
{
    int s;
    string input1;
    vector<string> expected_output;
    vector<string> actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int lineNo = 0;
    while (getline(infile, line))
    {
        if (lineNo == 0)
        {
            lineNo += 1;
            continue;
        }

        if (lineNo % 2 != 0)
        {
            input1 = line;
            stringstream ss(line);
            ss >> s;
        }

        else
        {
            line = RemoveAllPunctInArray(line);
            istringstream ss(line);
            string n;
            while (ss >> n)
            {
                expected_output.push_back(n);
            }

            actual_output = obj->fizzBuzz(s);

            if (checkOuputStringVec(expected_output, actual_output, input1))
            {
                return 0;
            }
            expected_output.clear();
            actual_output.clear();
        }
        lineNo += 1;
    }
    Success();
    return 0;
}
