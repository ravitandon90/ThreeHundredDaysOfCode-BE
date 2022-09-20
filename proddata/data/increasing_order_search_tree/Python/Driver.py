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
        root = createTreeFromArrayInput(line)
        actualOutput = solution.increasingBST(root)
    else:
        outputWithNoParentheses = line[1:-1].strip()
        expectedOutput = [] if outputWithNoParentheses == "" else [stringFormOfNode.strip()
                                                                   for stringFormOfNode in outputWithNoParentheses.split(',')]
        actualOutput = createArrayFromTreeInput(actualOutput)
        if actualOutput != expectedOutput:
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
