#include <bits/stdc++.h>
#include "Solution.hpp"
#include "helper.hpp"
#include <boost/tokenizer.hpp>
using namespace std;
using namespace boost;

int main()
{
    ifstream infile("../../testcases.txt");
    Solution *obj = new Solution();
    string line;
    vector<vector<int>> input;
    string expected_output;
    string actual_output;
    int status = 0;
    int lineNo = 1;
    while (getline(infile, line))
    {
        if (lineNo % 2 == 0)
        {
            status++;
            input = ParseLineToVectorOfVectors(line);
            bool res = obj->checkStraightLine(input);
            if (res)
                actual_output = "true";
            else
                actual_output = "false";

        }

        else if (lineNo != 1)
        {
            status++;
            string s1 = line;
            expected_output = line;

        
        }

        if (status % 2 == 0 && status != 0)
        {

            if (expected_output != actual_output)
            {
                cout << "Result: Failed" << endl;

                cout << "Actual Output: " << actual_output << endl;

                cout << "Expected Output: " << expected_output << endl;
            }
            else
                cout << "Result: Success" << endl;
            input.clear();
        }
        lineNo++;
    }
    return 0;
}
