class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def addTwoNumbers(self, l1, l2):
        word1 = ""
        word2 = ""

        while (l1):
            word1 += str(l1.val)
            l1 = l1.next

        while (l2):
            word2 += str(l2.val)
            l2 = l2.next

        word1 = word1[::-1]
        word2 = word2[::-1]

        num = str(int(word1) + int(word2))

        num = num[::-1]
        length = len(num)

        head = ListNode(int(num[0]))
        ptr = head

        for i in range(1, length):
            head.next = ListNode(int(num[i]))
            head = head.next

        return ptr
