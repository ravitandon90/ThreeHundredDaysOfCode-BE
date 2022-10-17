#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <queue>
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
        int minDepth(TreeNode *root)
        {
            if (root == NULL) return 0;
            queue<TreeNode*> Q;
            Q.push(root);
            int i = 0;
            while (!Q.empty())
            {
                i++;
                int k = Q.size();
                for (int j = 0; j < k; j++)
                {
                    TreeNode *rt = Q.front();
                    if (rt->left) Q.push(rt->left);
                    if (rt->right) Q.push(rt->right);
                    Q.pop();
                    if (rt->left == NULL && rt->right == NULL) return i;
                }
            }
            return -1;	
        }
};