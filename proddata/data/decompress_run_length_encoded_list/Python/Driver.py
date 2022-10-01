from Solution import Solution
from proddata.python.helpers import createIntArrayFromString
import sys
import os

# 2
# [1,2,3,4]
# [2,4,4,4]
# [1,1,2,3]
# [1,3,3]

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        input = createIntArrayFromString(line)
        actualOutput = solution.decompressRLElist(input)
    else:
        expectedOutput = createIntArrayFromString(line)
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
