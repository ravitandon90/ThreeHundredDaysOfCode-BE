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
    vector<int> nums1;
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
            parseIntOfVectorAndIntOfVector(line, nums,nums1);
        }
        else
        {
            actual_output = obj->buildTree(nums,nums1);
            line=RemoveAllPunctInArray(line);
            convertStringArrayToBinaryTree(expected_output,line);
            if (checkOutputBinaryTree(expected_output, actual_output, input1))
            {
                return 0;
            }
            nums.clear();
            nums1.clear();
        }
        status += 1;
    }
    Success();
    return 0;
}