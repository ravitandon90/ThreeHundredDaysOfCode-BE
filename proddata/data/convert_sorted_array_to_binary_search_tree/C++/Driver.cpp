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
#include <regex>

#include "Solution.hpp"
#include "../../cpp/helper.hpp"

using namespace std;



int main()
{
    string input1;
    vector<int> nums;
    int s = 0;
    TreeNode* expected_output;
    TreeNode* actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int status = 0;
    while (getline(infile, line))
    {
        if (status == 0)
        {
            status += 1;
            continue;
        }
        if (status % 2 != 0)
        {
            input1 = line;
            line=RemoveAllPunctInArray(line);
            stringstream ss(line);
            int x;
            while(ss>>x){
                nums.push_back(x);
            }
        }
        else
        {
            actual_output = obj->sortedArrayToBST(nums);
            line=RemoveAllPunctInArray(line);
            convertStringArrayToBinaryTree(expected_output,line);
            if (checkOutputBinaryTree(expected_output, actual_output, input1))
            {
                return 0;
            }
            nums.clear();
        }
        status += 1;
    }
    Success();
    return 0;
}