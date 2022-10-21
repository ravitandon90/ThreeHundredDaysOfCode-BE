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


class Solution {
public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
		int n = inorder.size();
        int Idx = 0;
        return helper(preorder, inorder, Idx, 0, n-1);
    }
    
    TreeNode* helper(vector<int>& preorder, vector<int>& inorder, int& Idx, int left, int right) {
        if (left > right) return NULL;
        int pivot = left; 
        while(inorder[pivot] != preorder[Idx]) pivot++;
        
        Idx++;
        TreeNode* newNode = new TreeNode(inorder[pivot]);
        newNode->left = helper(preorder, inorder, Idx, left, pivot-1);
        newNode->right = helper(preorder, inorder, Idx, pivot+1, right);
        return newNode;
    }
};