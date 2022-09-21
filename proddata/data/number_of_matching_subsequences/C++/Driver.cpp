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
    string s;
    vector<string> wordDict;
    int expected_output;
    int actual_output;
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
            int i = 0;
            while (ss >> t)
            {
                if (i == 0)
                {
                    s = t;
                }
                else
                {
                    wordDict.push_back(t);
                }
                i++;
            }
            actual_output = obj->numMatchingSubseq(s, wordDict);
        }
        else
        {
            stringstream ss(line);
            ss >> expected_output;

            if (expected_output != actual_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Input: ";
                cout << "s= " << s << ", ";
                cout << "words= ";
                for (auto x : wordDict)
                {
                    cout << x << " ";
                }
                cout << "\nExpected Output: " << expected_output << endl;
                cout << "Actual Output: " << actual_output << endl;
                return 0;
            }
            wordDict.clear();
        }
        lineNo += 1;
    }
    cout << "Result: Success" << endl;
    return 0;
}