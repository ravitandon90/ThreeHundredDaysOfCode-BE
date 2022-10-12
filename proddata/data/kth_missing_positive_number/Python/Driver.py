from Solution import Solution
from proddata.python.helpers import createIntArrayFromString, displayErrorMessage
import sys
import os

# 2
# [2,3,4,7,11], 5
# 9
# [1,2,3,4], 2
# 6

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
        target = int(line[pos + 1:].lstrip())
        array = createIntArrayFromString(line[:pos].rstrip())
        actualOutput = solution.findKthPositive(array, target)
    else:
        expectedOutput = int(line)
        if (actualOutput != expectedOutput):
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
