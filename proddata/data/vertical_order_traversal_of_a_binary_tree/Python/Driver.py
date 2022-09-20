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
        root = createTreeFromArrayInput(line)
        actualOutput = solution.verticalTraversal(root)
    else:
        expectedOutput = []
        if line[1:-1].strip() != "":
            splitArrays = line[1:-1].strip()[:-1].split(']')
            for array in splitArrays:
                pos = 0
                while array[pos] != '[':
                    pos += 1
                expectedOutput.append(
                    list(map(int, [s.strip() for s in array[pos + 1:].strip().split(',')])))
        if (actualOutput != expectedOutput):
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
