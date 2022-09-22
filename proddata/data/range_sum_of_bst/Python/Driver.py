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
        root = createTreeFromArrayInput(line[: pos + 1])
        while line[pos] != ',':
            pos += 1
        l, r = list(map(int, [s.strip() for s in line[pos + 1:].split(',')]))
        actualOutput = solution.rangeSumBST(root, l, r)
    else:
        expectedOutput = int(line)
        if actualOutput != expectedOutput:
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
