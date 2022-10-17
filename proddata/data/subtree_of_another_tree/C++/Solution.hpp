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


class Solution
{
    public:
        bool isSame(TreeNode *root, TreeNode *subRoot)
        {
            if (!root || !subRoot)
            {
                return !root && !subRoot;
            }
            return root->val == subRoot->val && isSame(root->left, subRoot->left) &&
                isSame(root->right, subRoot->right);
        }
    bool isSubtree(TreeNode *root, TreeNode *subRoot)
    {
        if (!root)
        {
            return false;
        }

        return isSame(root, subRoot) || isSubtree(root->left, subRoot) || isSubtree(root->right, subRoot);
    }
};