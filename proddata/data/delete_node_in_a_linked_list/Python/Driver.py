
from Solution import Solution, ListNode

import sys
import os

from proddata.python.helpers import removeAllPuncFromStringArray,displayErrorMessage

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 != 0:
        input = line
        values= removeAllPuncFromStringArray(line)
        head = ListNode(values[0])
        prev = head
        for i in range(1, len(values)-1):
            prev.next = ListNode(values[i])
            prev = prev.next
        y=head
        while(y):
            if y.val==values[-1]:
                solution.deleteNode(y)
            y=y.next
    else:
        y=head
        actualOutput=[]
        while(y):
            actualOutput.append(y.val)
            y=y.next
        expectedOutput=removeAllPuncFromStringArray(line)
        if actualOutput!=expectedOutput:
            displayErrorMessage(input,actualOutput,expectedOutput)
            isSolutionWrong=True
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
