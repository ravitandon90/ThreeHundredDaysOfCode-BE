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
    
    int check(TreeNode* root) {
        if(root == NULL) return 0;
        
        int lh=check(root->left);
        if(lh==-1) return -1;
        
        int rh=check(root->right);
        if(rh==-1) return -1;
        
        if(abs(lh-rh)>1) return -1;
        else return max(lh,rh) + 1;
    }
    
    bool isBalanced(TreeNode* root) {
        if(check(root)>=0) return true;
        else return false;
    }
};