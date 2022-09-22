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
    ifstream infile("testcases.txt");
    Solution *obj = new Solution();
    string line;
    vector<int> input;
    int expected_output = 0;
    int actual_output = 0;
    int status = 0;
    int lineNo = 0;
    while (getline(infile, line))
    {
        lineNo++;
        if (lineNo % 2 == 0)
        {
            status++;

            string s = line;

            int pos = 0;

            while (s[pos] != '\0')
            {
                if (s[pos] != ']' && s[pos] != ']' && s[pos] != ',')

                {
                    int numb = s[pos] - '0';
                    input.push_back(numb);
                }
                pos++;
            }

            actual_output = obj->numIdenticalPairs(input);

            input.clear();
        }
        else if (lineNo != 1)
        {
            status++;
            string s1 = line;
            expected_output = s1[0] - '0';
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
