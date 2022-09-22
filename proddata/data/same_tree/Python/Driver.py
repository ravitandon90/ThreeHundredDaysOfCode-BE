from Solution import Solution
from proddata.python.helpers import createTreeFromArrayInput, displayErrorMessage
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
        root1 = createTreeFromArrayInput(line[:pos + 1])
        while line[pos] != '[':
            pos += 1
        root2 = createTreeFromArrayInput(line[pos:])
        actualOutput = solution.isSameTree(root1, root2)
    else:
        expectedOutput = True if line == "true" else False
        if actualOutput != expectedOutput:
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
