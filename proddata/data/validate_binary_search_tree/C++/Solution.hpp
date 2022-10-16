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
    void helper(TreeNode* root, vector<int> & ans){
        if(root==NULL  ) return;
        helper(root->left, ans);
        ans.push_back(root->val);
         helper(root->right, ans);
     
    }
    bool isValidBST(TreeNode* root) {
        vector<int> ans;
        helper(root, ans);
       for(int i=0; i<ans.size()-1; i++){
           if( ans[i]>= ans[i+1] ) return false;
        }
        return true;
    }
};