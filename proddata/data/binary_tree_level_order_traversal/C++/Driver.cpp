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
#include "../../cpp/helper.hpp"

using namespace std;


void inOrder(TreeNode* root)
{
	if (root != NULL)
	{
		inOrder(root->left);
		cout << root->val<<endl;
		inOrder(root->right);
	}
}

int main()
{
    string input1;
    double v;
    TreeNode* nums=new TreeNode();
    vector<vector<int>> expected_output;
    vector<vector<int>> actual_output;
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
            string x = RemoveAllPunctInArray(line);
            convertStringArrayToBinaryTree(nums,x);
        }
        else
        {
            actual_output = obj->levelOrder(nums);
            parseVectorOfVector(line,expected_output);
            if (checkOuputIntVecOfVec(expected_output, actual_output, input1))
            {
                return 0;
            }
            expected_output.clear();
        }
        status += 1;
    }
    Success();
    return 0;
}