
from Solution import Solution, ListNode
import sys
import os


def add(data):
    newnode = ListNode()
    newnode.val = data
    newnode.next = None
    return newnode


def Print(actual_output, expected_output):
    curr = actual_output
    print("Actual Output: ", end="")
    while(curr != None):
        print(curr.val, end="->")
        curr = curr.next
    print()
    curr = expected_output
    print("Expected Output: ", end="")
    while(curr != None):
        print(curr.val, end="->")
        curr = curr.next


file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
curr = ListNode()
head1 = ListNode()
head2 = ListNode()
expected_output = ListNode()
actual_output = ListNode()
status = 0
c = 0
lineNo = 1
for line in Lines:
    if (lineNo == 1):
        ss = list(map(int, line.split()))
        for v in ss:
            if (c == 0):
                head1 = add(v)
                curr = head1
                c += 1
            else:
                curr.next = add(v)
                curr = curr.next

    if (lineNo == 2):
        ss = list(map(int, line.split()))
        for v in ss:
            if (c == 0):
                head2 = add(v)
                curr = head2
                c += 1
            else:
                curr.next = add(v)
                curr = curr.next

    if (lineNo == 3):
        ss = list(map(int, line.split()))
        for v in ss:
            if (c == 0):
                expected_output = add(v)
                curr = expected_output
                c += 1
            else:
                curr.next = add(v)
                curr = curr.next
        lineNo = 0

        obj = Solution()
        actual_output = obj.addTwoNumbers(head1, head2)
        curr1 = actual_output
        curr2 = expected_output

        while(curr1 != None and curr2 != None):
            if(curr1.val != curr2.val):
                print("Result: Failed")
                Print(actual_output, expected_output)
                status = 1
                break
            curr1 = curr1.next
            curr2 = curr2.next

    if status == 1:
        break

    c = 0
    lineNo += 1

if status == 0:
    print('Result: Success')
