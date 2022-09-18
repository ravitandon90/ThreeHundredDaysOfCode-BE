from unittest import expectedFailure
from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
n, k = 0, 0
status = 0
lineNo = 0
obj = Solution()
for line in Lines:
    if (lineNo % 2 == 0):
        n, k = map(int, line.split())

    else:
        actual_output = obj.uniquePaths(n, k)
        expected_output = line.strip()
        if (int(expected_output) != actual_output):
            print("Result: Failed")
            print('Actual Output:', actual_output)
            print('Expected Output:', int(expected_output))
            status = 1
            break
    lineNo += 1


if status == 0:
    print('Result: Success')
