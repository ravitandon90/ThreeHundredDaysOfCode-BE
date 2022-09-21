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
    int vectorSize;
    int n, k;
    int expected_output;
    int actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int status = 0;
    int lineNo = 0;
    while (getline(infile, line))
    {
        if (lineNo % 2 == 0)
        {
            stringstream ss(line);
            istream_iterator<int> begin(ss);
            istream_iterator<int> end;
            vector<int> input(begin, end);
            n = input[0];
            k = input[1];
        }
        else
        {
            stringstream ss(line);
            ss >> expected_output;
            actual_output = obj->firstBadVersion(n);
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