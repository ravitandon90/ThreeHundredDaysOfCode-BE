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
    TreeNode* increasingBST(TreeNode* root) {
        if (!root) {
            return nullptr;
        }
        
        TreeNode* left = increasingBST(root->left);
        TreeNode* right = increasingBST(root->right);
        
        root->left = nullptr;
        root->right = right;
        
		if (!left) {
            return root;
        }
        TreeNode* iter = left;
        while (iter && iter->right) {
            iter = iter->right;
        }
		
		iter->right = root;
        return left;
    }
};