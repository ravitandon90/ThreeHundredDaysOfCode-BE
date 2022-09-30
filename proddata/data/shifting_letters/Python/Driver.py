from Solution import Solution
from proddata.python.helpers import createIntArrayFromString, displayErrorMessage
import sys
import os

# 2
# "abc", [3,5,9]
# "rpl"
# "aaa", [1,2,3]
# "gfd"
#

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
        while line[pos] != ',':
            pos += 1
        s = line[:pos][1:-1].strip()
        shifts = createIntArrayFromString(line[pos + 1:])
        actualOutput = solution.shiftingLetters(s, shifts)
    else:
        expectedOutput = line[1:-1].strip()
        if (actualOutput != expectedOutput):
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
