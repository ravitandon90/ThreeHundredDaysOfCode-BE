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
//#include <boost/tokenizer.hpp>

#include "Solution.hpp"
//#include "helper.hpp"

using namespace std;
// using namespace boost;

int main()
{
    int numInputs = 0;
    int target = 0;
    vector<int> num;
    int k = 0;
    int expected_output;
    int actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int lineNo = 0;
    while (getline(infile, line))
    {
        lineNo += 1;

        if (lineNo == 1)
        {
            istringstream ss(line);
            int v;
            while (ss >> v)
            {
                num.push_back(v);
            }
        }
        if (lineNo == 2)
        {
            stringstream s(line);
            s >> k;
        }

        if (lineNo == 3)
        {
            stringstream ss(line);

            ss >> expected_output;

            actual_output = obj->subarraySum(num, k);

            if (actual_output != expected_output)
            {
                cout << "Result: Failed" << endl;
                cout << "Input: ";
                cout << "nums= ";
                for (auto x : num)
                {
                    cout << x << " ";
                }
                cout << ", k= " << k << endl;
                cout << "Expected Output: " << expected_output << endl;
                cout << "Actual Output: " << actual_output << endl;

                cout << endl;
                return 0;
            }

            num.clear();
            lineNo = 0;
        }
    }
    cout << "Result: Success" << endl;
    return 0;
}
