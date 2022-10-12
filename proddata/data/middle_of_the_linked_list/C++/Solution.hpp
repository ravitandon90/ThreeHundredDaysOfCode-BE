#include <vector>
#include <numeric>
using namespace std;



// Definition for singly-linked list.

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}

};

class Solution {
public:
    ListNode* middleNode(ListNode* head) {
        // ListNode *y=head;
        // while(y){
        //     cout<<y->val<<endl;
        //     y=y->next;
        // }
        if(head==NULL || head->next==NULL){
            return head;
        }
        
        ListNode* slow = head;
        ListNode* fast = head->next;
       
        while(fast!=NULL){
            fast = fast->next;
            
            if(fast!=NULL)
                fast = fast->next;
            slow = slow->next;
        }
        return slow;    
    }
};