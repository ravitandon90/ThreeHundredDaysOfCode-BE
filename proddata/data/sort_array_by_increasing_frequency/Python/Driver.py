from Solution import Solution
from proddata.python.helpers import createIntArrayFromString, displayErrorMessage
import sys
import os

# 3
# [1,1,2,2,2,3]
# [3,1,1,2,2,2]
# [2,3,1,3,2]
# [1,3,3,2,2]
# [-1,1,-6,4,5,-6,1,4,1]
# [5,-1,4,4,-6,-6,1,1,1]

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
        actualOutput = solution.frequencySort(input)
    else:
        expectedOutput = createIntArrayFromString(line)
        if (actualOutput != expectedOutput):
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
