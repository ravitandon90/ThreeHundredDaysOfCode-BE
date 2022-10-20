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
    bool isSameTree(TreeNode* p, TreeNode* q) {
        if(p==NULL && q == NULL)
            return true;
        if(p==NULL && q!=NULL)
            return false;
        if(p!=NULL && q==NULL)
            return false;
        
        bool left = isSameTree(p->left , q->left);
        bool right = isSameTree(p->right , q->right);
        
        bool curr = p->val == q->val;
        
        if(left && right && curr)
            return true;
        else
            return false;
    }
};