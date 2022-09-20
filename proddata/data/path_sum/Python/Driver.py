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
        pos = len(line) - 1
        while line[pos] != ',':
            pos -= 1
        reqdSum = int(line[pos + 1:].lstrip())
        root = createTreeFromArrayInput(line[:pos].rstrip())
        actualOutput = solution.hasPathSum(root, reqdSum)
    else:
        expectedOutput = True if line == "true" else False
        if actualOutput != expectedOutput:
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
