from Solution import Solution
from proddata.python.helpers import createIntArrayFromString
import sys
import os

# 2
# [1,2,2,1], [2,2]
# [2]
# [4,9,5], [9,4,9,8,4]
# [9,4]

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        pos = 0
        while not line[pos] == ']':
            pos += 1
        array1 = createIntArrayFromString(line[0: pos + 1])
        while not line[pos] == '[':
            pos += 1
        array2 = createIntArrayFromString(line[pos:])
        actualOutput = solution.intersection(array1, array2)
    else:
        expectedOutput = createIntArrayFromString(line)
        actualOutput.sort()
        expectedOutput.sort()
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
