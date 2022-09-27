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

    vector<int> nums;
    vector<int> expected_output;
    vector<int> actual_output;
    ifstream infile("testcases.txt");
    Solution *obj = new Solution();
    string line;
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
                if (s[pos] != ']' && s[pos] != '[' && s[pos] != ',')

                {
                    int numb = s[pos] - '0';
                    nums.push_back(numb);
                }
                pos++;
            }
            actual_output = obj->decompressRLElist(nums);
            nums.clear();
           
        }

        else if (lineNo != 1)
        {
            status++;
            int pos = 0;
            string s1 = line;
            while (s1[pos] != '\0')
            {
                if (s1[pos] != ']' && s1[pos] != '[' && s1[pos] != ',')

                {
                    int numb = s1[pos] - '0';
                    expected_output.push_back(numb);
                }
                pos++;
            }
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
     
        expected_output.clear();
    }

    return 0;
}
