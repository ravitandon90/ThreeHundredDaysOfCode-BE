#include <vector>
#include <numeric>
#include <map>
#include <queue>

using namespace std;


struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};


// // Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

TreeNode* newNode(int val)
{
    TreeNode* node = (TreeNode*)malloc(sizeof(TreeNode));
	node->val = val;
	node->left = node->right = NULL;
	return (node);
}


class Solution {
public:
    int rangeSumBST(TreeNode* root, int low, int high) {
        if(!root)
            return 0;
        if(root->val>high)
            return rangeSumBST(root->left,low,high);
        if(root->val<low)
            return rangeSumBST(root->right,low,high);
        return root->val+ rangeSumBST(root->left,low,high)+ rangeSumBST(root->right,low,high);
    }
};