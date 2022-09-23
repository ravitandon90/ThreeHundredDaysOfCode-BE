#include <bits/stdc++.h>
using namespace std;
#include "Solution.hpp"

int main()
{
    ifstream infile("testcases.txt");
    Solution *obj = new Solution();
    string line;
    vector<int> input;
    int expected_output = 0;
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
            stringstream ss(s);

            int number = 0;
            while (ss >> number)
            {
                cout << "\nHello";
                
                input.push_back(number);
            }
    
            actual_output = obj->hIndex(input);

            input.clear();
        }
        else if (lineNo != 1)
        {
            status++;
            string s1 = line;
            stringstream ss(s1);
            int number1;
            ss >> expected_output;
        }
        if (status % 2 == 0 && status != 0)
        {

            if (expected_output != actual_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Actual Output: " << actual_output << endl;
                cout << "Expected Output: " << expected_output << endl;
                return 0;
            }
            else
                cout << "Result: Success" << endl;
        }
    }
    return 0;
}
