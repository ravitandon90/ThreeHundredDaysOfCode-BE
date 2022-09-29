from Solution import Solution
from proddata.python.helpers import createIntArrayFromString
import sys
import os

# 2
# [1,3,5,4,7]
# 3
# [2,2,2,2,2]
# 1

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        input = createIntArrayFromString(line)
        actualOutput = solution.findLengthOfLCIS(input)
    else:
        expectedOutput = int(line)
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
