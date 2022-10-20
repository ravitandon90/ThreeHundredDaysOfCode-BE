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

void LevelOrder(TreeNode* &root)
{
    if (root == NULL)
        return;
 
    queue<TreeNode*> q;
 
    q.push(root);
 
    while (q.empty() == false) {
        TreeNode* node = q.front();
        cout<<( node->val)<<" ";
        q.pop();

        if (node->left != NULL)
            q.push(node->left);
 
        if (node->right != NULL)
            q.push(node->right);
    }
}

int main()
{
    string input1;
    vector<string> nums;
    TreeNode* nums1;
    int m,n;
    int s = 0;
    int expected_output;
    int actual_output;
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
            parseIntOfVectorAndIntAndInt(line, nums,m,n);
            convertStringArrayToBinaryTree(nums1,nums); 
        }
        else
        {
            actual_output = obj->rangeSumBST(nums1,m,n);
            stringstream ss(line);
            ss>>expected_output;
            if (checkOuputInt(expected_output, actual_output, input1))
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