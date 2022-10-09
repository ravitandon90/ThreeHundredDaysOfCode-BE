from Solution import Solution
from proddata.python.helpers import createIntArrayFromString, displayErrorMessage
import sys
import os

# 2
# [1,2,3,4,5,6,7], 3
# [5,6,7,1,2,3,4]
# [-1,-100,3,99], 2
# [3,99,-1,-100]

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
        k = int(line[pos + 1:].lstrip())
        nums = createIntArrayFromString(line[:pos])
        solution.rotate(nums, k)
    else:
        expectedOutput = createIntArrayFromString(line)
        if (nums != expectedOutput):
            displayErrorMessage(testCase, nums, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
