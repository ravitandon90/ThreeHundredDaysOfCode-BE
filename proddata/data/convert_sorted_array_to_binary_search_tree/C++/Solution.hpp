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
    TreeNode * bst(vector<int>&nums, int left, int right){
        if(left <= right){
            int mid = (left + right)/2;
            TreeNode * node = new TreeNode(nums[mid]);
            node -> left = bst(nums, left, mid - 1);
            node -> right = bst(nums, mid + 1, right);
            return node;
        }
        return NULL;
    }
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        int n = nums.size();
        return bst(nums, 0, n - 1);
    }
};