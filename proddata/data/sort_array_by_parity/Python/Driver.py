from Solution import Solution
from proddata.python.helpers import createIntArrayFromString, displayErrorMessage
import sys
import os

# 2
# [3,1,2,4]
# [2,4,3,1]
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
        actualOutput = solution.sortArrayByParity(input)
    else:
        expectedOutput = createIntArrayFromString(line)
        oddStarted = False
        for num in actualOutput:
            if num % 2 == 1:
                oddStarted = True
            elif oddStarted:
                isSolutionWrong = True
                break
        if (isSolutionWrong):
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
