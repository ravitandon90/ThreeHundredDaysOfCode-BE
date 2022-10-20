#include <bits/stdc++.h>
using namespace std;
#include "Solution.hpp"
#include "helper.hpp"

int main()
{
    ifstream infile(".././testcases.txt");
    Solution *obj = new Solution();
    string line;

    vector<int> expected_output;
    vector<int> actual_output;
    int status = 0;

    vector<int> input1;
    vector<int> input2;
    vector<int> input11;
    vector<int> input21;
    vector<int> output;
    int lineNo = 1;
    int m = 0, n = 0;
    while (getline(infile, line))
    {

        if (lineNo % 2 == 0)
        {
            status++;
            string s1 = line;

            int pos = 0;
            int k = 0;
            bool first = false;
            bool second = false;
            bool m1 = false;
            bool n1 = false;
            string g3 = "";

            while (s1[pos] != '\0')
            {

                if (s1[pos] == '[' && first == false)
                {
                    while (s1[pos] != ']')
                    {

                        if (s1[pos] != ',' && s1[pos] != '[')
                        {
                            string g = "";

                            g = g + s1[pos];
                            stringstream ss(g);
                            int v;
                            while (ss >> v)
                                input1.push_back(v);

                            // cout << "\n"
                            //      << g;
                        }
                        pos++;
                    }
                    first = true;
                }

                if (s1[pos] == ',' && first == true && second == false)
                {
                    string mi = "";
                    pos++;
                    while (s1[pos] != ',')
                    {
                        mi = mi + s1[pos];
                        pos++;
                    }
                    //  cout << "\n mi string :" << mi;
                    stringstream ss(mi);
                    while (ss >> m)
                        input11.push_back(m);
                }
                if (s1[pos] == '[' && second == false && first == true)
                {
                    while (s1[pos] != ']')
                    {

                        if (s1[pos] != ',' && s1[pos] != '[')
                        {
                            string g1 = "";

                            g1 = g1 + s1[pos];
                            stringstream ss(g1);
                            int v1;
                            while (ss >> v1)
                                input2.push_back(v1);

                            // cout << "\n"
                            //      << g;
                        }
                        pos++;
                    }
                    second = true;
                }
                if (s1[pos] == ',' && second == true)
                {
                    string mi1 = "";
                    pos++;
                    while (s1[pos] != ',')
                    {
                        mi1 = mi1 + s1[pos];
                        pos++;
                    }
                    //  cout << "\n mi1 string :" << mi1;
                    stringstream ss(mi1);
                    while (ss >> n)
                        input21.push_back(n);
                    n1 = true;
                }
                pos++;
            }
            actual_output = obj->merge(input1, input11[0], input2, input21[0]);
        }
        else if (lineNo != 1)
        {
            status++;
            string s1;

            s1 = line;
            cout << "\n"
                 << line;
            stringstream ss(s1);
            int number1;
            while (ss >> number1)
                expected_output.push_back(number1);
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

            input11.clear();
            input1.clear();
            input21.clear();
            input2.clear();
            expected_output.clear();
            actual_output.clear();
        }
        lineNo++;
    }
    return 0;
}
