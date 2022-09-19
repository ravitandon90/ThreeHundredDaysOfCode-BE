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
    Count += 1
    if (Count == 1):
        num = list(map(int, line.split()))
        nums = num[:-1]
        k = num[-1]
    if (Count == 2):
        expected_output = int(line.strip())
        actual_output = obj.findTargetSumWays(nums, k)
        if (expected_output != actual_output):
            print('Result: Failed')
            print("Input: nums: {}, target: {}".format(nums, k))
            print("Expected Outuput:", expected_output)
            print("Actual Output:", actual_output)
            status = 1
            break
        Count = 0
if (status == 0):
    print('Result: Success')
