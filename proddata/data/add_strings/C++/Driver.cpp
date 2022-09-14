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
// #include <boost/tokenizer.hpp>

#include "Solution.hpp"
// #include "helper.hpp"

using namespace std;
// using namespace boost;

int main()
{
    int numInputs = 0;
    int target = 0;
    vector<int> input;
    string expected_output;
    string actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int lineNo = 0;
    while (getline(infile, line))
    {
        if ((lineNo % 2) == 0)
        {
            string s = line;
            stringstream ss(s);
            istream_iterator<string> begin(ss);
            istream_iterator<string> end;
            vector<string> input(begin, end);
            actual_output = obj->addStrings(input[0], input[1]);
        }
        else
        {
            expected_output = line;
            if (expected_output != actual_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Actual Output:" << actual_output << endl;
                cout << "Expected Output:" << expected_output << endl;
                return 0;
            }
        }
        lineNo += 1;
    }
    cout << "Result: Success" << endl;
    return 0;
}