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

void inorder(TreeNode* &node){
    if (node!=NULL){
        inorder(node->left);
        cout<<( node->val)<<endl;;
        inorder(node->right);
    }
}

int main()
{
    string input1;
    vector<string> nums;
    vector<string> nums1;
    TreeNode* x1;
    TreeNode* x2;
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
            parseStringOfVectorAndStringOfVector(line, nums,nums1);
            convertStringArrayToBinaryTree(x1,nums);
            convertStringArrayToBinaryTree(x2,nums1);
        }
        else
        {
            actual_output = obj->mergeTrees(x1,x2);
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