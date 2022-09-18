#include <bits/stdc++.h>
using namespace std;
#include "Solution.hpp"
void Print(vector<int> actual_output, vector<int> expected_output)
{
    cout << "Result: Failed" << endl;
    cout << "Actual Output:" << endl;
    for (int i = 0; i < actual_output.size(); i++)
    {
        cout << actual_output[i] << " ";
    }
    cout << endl;

    cout << "Expected Output:" << endl;
    for (int i = 0; i < expected_output.size(); i++)
    {
        cout << expected_output[i] << " ";
    }
    cout << endl;
}

int main()
{

    vector<int> input;
    vector<int> expected_output;
    vector<int> actual_output;
    ifstream infile("testcases.txt");
    Solution *obj = new Solution();
    string line;
    int testcases;
    int lineNo = 0;
    while (getline(infile, line))
    {
        lineNo++;
        if (lineNo % 2 == 0)
        {

            // string g = line;
            // stringstream ss(g);
            // ss >> testcases;

            string s = line;
            stringstream ss(s);
            // istream_iterator<int> begin(ss);
            // istream_iterator<int> end;
            // vector<int> input(begin, end);

            int number;
            while (ss >> number)
                input.push_back(number);

            actual_output = obj->asteroidCollision(input);
            lineNo++;
            input.clear();
        }
        else if (lineNo != 1)
        {
            string s1 = line;
            stringstream ss(s1);
            int number1;
            while (ss >> number1)
                expected_output.push_back(number1);
            lineNo++;
        }
        if (expected_output != actual_output)
        {
            Print(actual_output, expected_output);
            return 0;
        }
        else
            cout << "Result: Success" << endl;
    }
    return 0;
}
