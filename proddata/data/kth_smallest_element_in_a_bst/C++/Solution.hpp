#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;


// Definition for singly-linked list.
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
        int a[10001] = { 0 };

    int count = 0;

    void traversal(TreeNode *root)
    {
        if (!root) return;

        traversal(root->left);
        a[count++] = root->val;
        traversal(root->right);
    }
    int kthSmallest(TreeNode *root, int k)
    {
        traversal(root);
        return a[k - 1];
    }
};