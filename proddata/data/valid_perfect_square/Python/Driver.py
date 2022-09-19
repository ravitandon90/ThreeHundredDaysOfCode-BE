from itertools import count
from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
Count = 0
status = 0
obj = Solution()
for line in Lines:
    if (Count % 2 == 0):
        a = int(line.strip())
        actual_output = obj.isPerfectSquare(a)
    else:
        expected_output = line.strip()
        if (expected_output != str(actual_output).lower()):
            print('Result: Failed')
            print("Input: num= {}".format(a))
            print("Actual Output:", actual_output)
            print("Expected Outuput:", expected_output)
            status = 1
            break
    Count += 1
if status == 0:
    print('Result: Success')
