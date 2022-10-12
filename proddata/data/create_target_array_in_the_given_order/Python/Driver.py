from Solution import Solution
from proddata.python.helpers import createIntArrayFromString, displayErrorMessage
import sys
import os

# 3
# [0,1,2,3,4], [0,1,2,2,1]
# [0,4,1,3,2]
# [1,2,3,4,0], [0,1,2,3,0]
# [0,1,2,3,4]
# [1], [0]
# [1]

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
        nums = createIntArrayFromString(line[:pos + 1])
        stringFormOfSecondArray = line[pos + 1:].lstrip()[1:].lstrip()
        indices = createIntArrayFromString(stringFormOfSecondArray)
        actualOutput = solution.createTargetArray(nums, indices)
    else:
        expectedOutput = createIntArrayFromString(line)
        if (actualOutput != expectedOutput):
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
