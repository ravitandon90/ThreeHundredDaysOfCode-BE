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


class Solution {
public:
    int maxDepth(TreeNode* root) {
        int a=1;
        int b=1;
        if(root==NULL){
            return 0;
        }
         a+=maxDepth(root->left);
         b+=maxDepth(root->right);
         int result=max(a,b);
         return result;
    }
};