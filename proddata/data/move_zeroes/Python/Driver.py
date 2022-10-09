from cgi import test
from Solution import Solution
from proddata.python.helpers import createIntArrayFromString, displayErrorMessage
import sys
import os

# 2
# [0,1,0,3,12]
# [1,3,12,0,0]
# [0]
# [0]

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        testCase = line
        input = createIntArrayFromString(line)
        solution.moveZeroes(input)
    else:
        expectedOutput = createIntArrayFromString(line)
        if (input != expectedOutput):
            displayErrorMessage(testCase, input, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
