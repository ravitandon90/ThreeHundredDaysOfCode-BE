#include <vector>
#include <numeric>
using namespace std;



// Definition for singly-linked list.

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(NULL) {}

};

class Solution {
public:
    ListNode* swapPairs(ListNode* head) {
       if(head == nullptr || head -> next == nullptr) 
        {
            return head;
        }
        ListNode* temp= new ListNode(); 
        temp = head->next; 
        head->next = swapPairs(head->next->next); 
        temp->next = head; 
        return temp; 
    }
};