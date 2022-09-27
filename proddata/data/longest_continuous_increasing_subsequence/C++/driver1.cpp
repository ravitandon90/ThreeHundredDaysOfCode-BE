#include <bits/stdc++.h>
using namespace std;
#include "Solution.hpp"
void Print(int actual_output, int expected_output)
{
    cout << "Result: Failed" << endl;
    cout << "Actual Output: " << actual_output << endl;
    cout << "Expected Output: " << expected_output << endl;
}
int main()
{
    Solution *sst = new Solution();
    ifstream infile("testcases.txt");

    string line;
    vector<int> input;
    int expected_output;
    int actual_output;
    int status = 0;
    int lineNo = 0;
    while (getline(infile, line))
    {
        lineNo++;
        if (lineNo % 2 == 0)
        {
            status++;

            string s = line;
            stringstream ss(line);

            int number;
            while (ss >> number)
                input.push_back(number);

            actual_output = sst->findLengthOfLCIS(input);

            input.clear();
        }
        else if (lineNo != 1)
        {
            status++;
            string s1 = line;
            stringstream ss(s1);
            int number1;
            while (ss >> number1)
                expected_output = number1;
        }
        if (status % 2 == 0 && status != 0)
        {
            if (expected_output != actual_output)
            {
                Print(actual_output, expected_output);
                return 0;
            }
            else
                cout << "Result: Success" << endl;
        }
    }
    return 0;
}
