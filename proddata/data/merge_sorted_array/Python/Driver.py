from Solution import Solution
from proddata.python.helpers import createIntArrayFromString
import sys
import os

# 3
# [1,2,3,0,0,0], 3, [2,5,6], 3
# [1,2,2,3,5,6]
# [1], 1, [], 0
# [1]
# [0], 0, [1], 1
# [1]


def helper(input):
    splitByComma = input.split(',')
    length = int(splitByComma[-1].strip())
    arr = createIntArrayFromString(','.join(splitByComma[:-1]))
    return (arr, length)


file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        arr1, arr2 = [], []
        pos = len(line) - 1
        while line[pos] != '[':
            pos -= 1
        arr2, n = helper(line[pos:])
        while line[pos] != ',':
            pos -= 1
        arr1, m = helper(line[:pos])
        solution.merge(arr1, m, arr2, n)
    else:
        expectedOutput = createIntArrayFromString(line)
        if (arr1 != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", arr1)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
