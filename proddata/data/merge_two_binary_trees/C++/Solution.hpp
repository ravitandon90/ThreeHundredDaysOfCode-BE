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
        TreeNode* mergeTrees(TreeNode *root1, TreeNode *root2)
        {
            if (root1 == NULL)
                return root2;
            if (root2 == NULL)
                return root1;
            TreeNode *temp = new TreeNode(root1->val + root2->val);

            if (root1->left != NULL || root2->left != NULL)
            {
                temp->left = mergeTrees(root1->left, root2->left);
            }
            if (root1->right != NULL || root2->right != NULL)
            {
                temp->right = mergeTrees(root1->right, root2->right);
            }
            return temp;
        }
};