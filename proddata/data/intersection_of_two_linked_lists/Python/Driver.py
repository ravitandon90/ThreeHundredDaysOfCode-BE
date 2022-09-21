from Solution import Solution, ListNode
from proddata.python.helpers import createLLFromArrayInput, createArrayFromLinkedList, displayErrorMessage
import sys
import os

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        testCase = line
        ll1Starts, ll1Ends = line.find('['), line.find(']')
        pointOfIntersection = int(line[: ll1Starts].rstrip()[:-1].rstrip())
        ll1 = createLLFromArrayInput(line[ll1Starts: ll1Ends + 1])
        ll2Starts, ll2Ends = line.find(
            '[', ll1Ends + 1), line.find(']', ll1Ends + 1)
        ll2 = createLLFromArrayInput(line[ll2Starts: ll2Ends + 1])
        while line[ll2Ends] != ',':
            ll2Ends += 1
        nodesBeforeIntersection1, nodesBeforeIntersection2 = list(
            map(int, [s.strip() for s in line[ll2Ends + 1:].split(',')]))
        intersectionNode = None
        if pointOfIntersection > 0:
            intersectionNode, count = ll1, 0
            while count < nodesBeforeIntersection1:
                intersectionNode, count = intersectionNode.next, count + 1
            dummy = ListNode(-1)
            dummy.next = ll2
            node, count = dummy, 0
            while count < nodesBeforeIntersection2:
                node, count = node.next, count + 1
            node.next = intersectionNode
        actualOutput = solution.getIntersectionNode(ll1, ll2)
    else:
        expectedOutput = []
        if line != "No intersection":
            expectedOutput = createArrayFromLinkedList(intersectionNode)
        actualOutput = createArrayFromLinkedList(actualOutput)
        if actualOutput != expectedOutput:
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
