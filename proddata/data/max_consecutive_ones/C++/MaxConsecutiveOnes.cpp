#include <bits/stdc++.h>
using namespace std;
#include "Solution.hpp"
void Print(int actual_output, int expected_output)
{
    cout << "Result: Failed" << endl;
    cout << "Actual Output:" << actual_output << endl;
    cout << "Expected Output:" << expected_output << endl;
   
}
int main()
{
    Solution *obj = new Solution();

    ifstream infile("testcases.txt");
    string line;

    // int lineNo = 0;
    vector<int> input1;
    int expected_output = 0;
    int actual_output = 0;
    int status = 1;

    int lineNo = 0;
    while (getline(infile, line))
    {
        lineNo++;
        if (lineNo % 2 == 0)
        {
            status++;
            string s = line;
        
            stringstream ss(s);
            int number;
            while (ss >> number)
                input1.push_back(number);
            actual_output = obj->findMaxConsecutiveOnes(input1);
            lineNo++;
        }
        else if (lineNo != 1 && lineNo % 2 != 0)
        {
            status++;
            string s = line;
            cout << "\n"
                 << s[0];
            expected_output = s[0] - '0';
            lineNo++;

        }
        if (status % 2 == 0 && status != 0)
        {

            if (actual_output != expected_output)
            {
                Print(actual_output, expected_output);
                return 0;
            }
            else
                cout << "Result: Success" << endl;
        }
    }
}
