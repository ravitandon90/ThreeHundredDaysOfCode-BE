# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def swapPairs(self, head):
        h = head
        while head:
            if head.next:
                head.val, head.next.val = head.next.val, head.val
                head = head.next.next
            else:
                break
        return h