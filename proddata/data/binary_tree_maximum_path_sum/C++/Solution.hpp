#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <queue>
#include <bits/stdc++.h>
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
        int solveKardenge(TreeNode *root, int &maxi)
        {
            if (root == NULL)
            {
                return 0;
            }

            int left = max(0, solveKardenge(root->left, maxi));
            int right = max(0, solveKardenge(root->right, maxi));

            maxi = max(left + right + root->val, maxi);

            return max(left, right) + root->val;
        }

    int maxPathSum(TreeNode *root)
    {
        int maxi = INT_MIN;
        int res = solveKardenge(root, maxi);

        return maxi;
    }
};