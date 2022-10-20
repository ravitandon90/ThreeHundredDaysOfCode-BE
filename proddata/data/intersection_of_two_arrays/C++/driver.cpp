#include <bits/stdc++.h>
using namespace std;
#include "Solution.hpp"
#include "../../cpp/helper.hpp"
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
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    vector<int> nums1, nums2;
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
            int pos = 0;
            string s = line;
            while (s[pos] != '\0')
            {
                if (s[pos] == '[')
                {
                    pos++;
                    while (s[pos] != ']')
                    {

                        if (s[pos] != ',')
                        {

                            char x[] = {s[pos]};

                            int number = stoi(x);
                            nums1.push_back(number);
                        }
                        pos++;
                    }
                }
                pos++;
                if (s[pos] == ',')
                {
                    pos++;
                    if (s[pos] == '[')
                    {
                        pos++;
                        while (s[pos] != ']')
                        {

                            if (s[pos] != ',')
                            {

                                char x[] = {s[pos]};

                                int number = stoi(x);

                                nums2.push_back(number);
                            }
                            pos++;
                        }
                    }
                }
            }

            actual_output = obj->intersection(nums1, nums2);

            nums1.clear();
            nums2.clear();
        }

        else if (lineNo != 1 && lineNo % 2 != 0)
        {
            status++;
            string s1 = line;
            string s = line;

            stringstream ss(s);
            int number;
            while (ss >> number)
                expected_output.push_back(number);
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
