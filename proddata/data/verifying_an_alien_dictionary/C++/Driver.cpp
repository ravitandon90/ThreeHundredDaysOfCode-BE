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
    int numInputs = 0;
    int target = 0;
    string order;
    vector<string> words;
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
            string x = line;
            stringstream ss(x);
            string t;
            while (ss >> t)
            {
                words.push_back(t);
            }
            order = words.back();
            words.pop_back();
            bool res = obj->isAlienSorted(words, order);
            if (res)
            {
                actual_output = "true";
            }
            else
            {
                actual_output = "false";
            }
        }
        else
        {
            expected_output = line;

            if (expected_output != actual_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Input: ";
                cout << "words= ";
                for (auto x : words)
                {
                    cout << x << " ";
                }
                cout << ", order= " << order << ", ";
                cout << "\nExpected Output: " << expected_output << endl;
                cout << "Actual Output: " << actual_output << endl;
                return 0;
            }
            words.clear();
        }
        lineNo += 1;
    }
    cout << "Result: Success" << endl;
    return 0;
}