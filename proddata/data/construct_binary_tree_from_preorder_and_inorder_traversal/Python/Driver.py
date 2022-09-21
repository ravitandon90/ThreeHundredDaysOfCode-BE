from Solution import Solution
from proddata.python.helpers import createTreeFromArrayInput, createArrayFromTreeInput, displayErrorMessage
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
        pos = 0
        while line[pos] != ']':
            pos += 1
        preorder = list(map(int, [s.strip() for s in line[1:pos].split(',')]))
        while line[pos] != '[':
            pos += 1
        inorder = list(map(int, [s.strip()
                       for s in line[pos + 1: -1].split(',')]))
        actualOutput = solution.buildTree(preorder, inorder)
    else:
        expectedOutput = [s.strip() for s in line[1:-1].split(',')]
        actualOutput = createArrayFromTreeInput(actualOutput)
        if actualOutput != expectedOutput:
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
