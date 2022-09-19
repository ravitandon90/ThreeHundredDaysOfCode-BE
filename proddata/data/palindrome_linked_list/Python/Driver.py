from Solution import Solution, ListNode
import sys
import os


def createLL(values):
    head = ListNode(values[0])
    prev = head
    for i in range(1, len(values)):
        prev.next = ListNode(values[i])
        prev = prev.next
    return head


def convertLL(node):
    res = []
    while node:
        res.append(node.val)
        node = node.next
    return res


def displayErrorMessage(testCase, actualOutput, expectedOutput):
    print("Result: Failed for test case: ", testCase)
    print("Actual Output: ", actualOutput)
    print("Expected Output: ", expectedOutput)


file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        testCase = line
        values = list(map(int, [s.strip() for s in line[1:-1].split(',')]))
        ll = createLL(values)
        actualOutput = solution.isPalindrome(ll)
    else:
        expectedOutput = True if line == "true" else False
        if actualOutput != expectedOutput:
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
