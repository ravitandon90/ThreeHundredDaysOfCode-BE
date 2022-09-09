#include <algorithm>
#include <fstream>
#include<iostream>
#include <stack>
#include<string>
#include<vector>
#include<unordered_map>
#include<unordered_set>
#include <boost/tokenizer.hpp>

#include "Solution.hpp"
#include "../../cpp/helper.hpp"

using namespace std;
using namespace boost;

int main() {
    int numInputs = 0;
    int target = 0;
    vector<int> input;
    vector<vector<int> > expected_output;
    vector<vector<int> > actual_output;
    ifstream infile("testcases.txt");
    int lineNo = -1;
    Solution* s = new Solution();
    string line;
    while(getline(infile, line)) {
      lineNo++;
      if (lineNo == 0) {
        numInputs = stoi(line);
      } else if ((lineNo % 2) == 1) {
        input = ParseLineToVector(line);
        target = input.at(input.size() - 1);
        input.pop_back();
      } else {
        expected_output = ParseLineToVectorOfVectors(line);
        actual_output = s->fourSum(input, target);
        if (not Compare(expected_output, actual_output)) {
          cout << "Actual Output:" << Print(actual_output) << endl;
          cout << "Expected Output:" << Print(expected_output) << endl;
        }
      }
    }
    return 0;
}