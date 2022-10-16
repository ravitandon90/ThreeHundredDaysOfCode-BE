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


class Solution {
    int findsum(TreeNode * root, int &tilt){
        if(!root) return 0;
        int l =findsum(root->left,tilt);
        int r = findsum( root->right,tilt);
        tilt +=abs(l-r);
        return l + r + root->val;
    }
public:
    int findTilt(TreeNode* root) {
      int tilt = 0;
      findsum(root,tilt);
        return tilt;
    }
};