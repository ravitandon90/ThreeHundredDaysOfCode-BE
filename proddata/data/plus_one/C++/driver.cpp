#include <bits/stdc++.h>
using namespace std;
#include "Solution.hpp"
#include "C:/Users/hp/Documents/GitHub/ThreeHundredDaysOfCode-BE/proddata/cpp/helper1.hpp"


void Print(vector<int> actual_output, vector<int> expected_output)
{
    cout << "Result: Failed" << endl;
    cout << "Actual Output: " << endl;
    for (int i = 0; i < actual_output.size(); i++)
    {
        cout << actual_output[i] << " ";
    }
    cout << endl;
    cout << "Expected Output: " << endl;
    for (int i = 0; i < expected_output.size(); i++)
    {
        cout << expected_output[i] << " ";
    }
    cout << endl;
}
int main()
{

    ifstream infile("testcases.txt");
    Solution *obj = new Solution();
    string line;
    vector<int> input;
    vector<int> expected_output;
    vector<int> actual_output;
    int status = 0;
    int lineNo = 0;
    while (getline(infile, line))
    {
        lineNo++;
        if (lineNo % 2 == 0)
        {
            status++;

            string s = line;
            string x = RemoveAllPunctInArray(line);

            stringstream ss(x);

            int number;
            while (ss >> number)
            {
                input.push_back(number);
            }
            
        }
        else if (lineNo != 1)
        {

            status++;
            string s1 = RemoveAllPunctInArray(line);

            stringstream ss(s1);
            int number1;
            while (ss >> number1)
                expected_output.push_back(number1);
        }
        if (status % 2 == 0 && status != 0)
        {
            actual_output = obj->plusOne(input);

            if (expected_output != actual_output)
            {
                Print(actual_output, expected_output);
                return 0;
            }
            else
                cout << "Result: Success" << endl;
            input.clear();
            expected_output.clear();
        }
    }
    return 0;
}
