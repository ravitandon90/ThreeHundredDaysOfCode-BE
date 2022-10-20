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



class Solution {
public:
    
    vector<vector<int>> solve(vector<TreeNode*> k, vector<vector<int>> res){
        if (k.size()==0) return res;
        
        vector<TreeNode*> newk;
        vector<int> lvl;
        
        for (int i=0; i < k.size(); i++){
            lvl.push_back( k[i] -> val );
            if ( k[i] -> left ) newk.push_back( k[i] -> left);
            if ( k[i] -> right ) newk.push_back( k[i] -> right);
        }
        
        res.push_back(lvl);
        return solve(newk, res);
        
    }   
    
    vector<vector<int>> levelOrder(TreeNode* root) {
        
        vector<TreeNode*> k{root};
        vector<vector<int>> res;
        
        if (root==NULL) return res;
        
        return solve(k,res);
        
    }
};