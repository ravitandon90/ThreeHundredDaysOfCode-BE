from Solution import Solution
from proddata.python.helpers import createIntArrayFromString, displayErrorMessage
import sys
import os

# 3
# [2,3,5,1,3], 3
# [true,true,true,false,true]
# [4,2,1,1,2], 1
# [true,false,false,false,false]
# [12,1,12], 10
# [true,false,true]

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
        extraCandies = int(line[pos + 1:].lstrip())
        candies = createIntArrayFromString(line[:pos])
        actualOutput = solution.kidsWithCandies(candies, extraCandies)
    else:
        expectedOutputAsArray = [s.strip() for s in line[1:-1].split(',')]
        for i in range(len(expectedOutputAsArray)):
            expectedOutput = True if expectedOutputAsArray[i] == "true" else False
            if (actualOutput[i] != expectedOutput):
                isSolutionWrong = True
                break
        if isSolutionWrong:
            displayErrorMessage(testCase, actualOutput, expectedOutputAsArray)
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
