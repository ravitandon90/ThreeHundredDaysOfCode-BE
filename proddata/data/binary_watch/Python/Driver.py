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
    if (Count == 0):
        Count += 1
        continue
    if (Count % 2 != 0):
        k = int(line.strip())
    else:
        expected_output = list(map(str, line.split()))
        actual_output = obj.readBinaryWatch(k)
        actual_output.sort()
        expected_output.sort()
        if (expected_output != actual_output):
            print('Result: Failed')
            print("Actual Output:", actual_output)
            print("Expected Outuput:", expected_output)
            status = 1
            break
    Count += 1
if (status == 0):
    print('Result: Success')
