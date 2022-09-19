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
    if (Count == 2):
        k = int(line)
    if (Count == 3):
        expected_output = int(line.strip())
        actual_output = obj.subarraySum(num, k)
        if (expected_output != actual_output):
            print('Result: Failed')
            print("Input: nums: {}, k: {}".format(num, k))
            print("Actual Output:", actual_output)
            print("Expected Outuput:", expected_output)
            status = 1
            break
        Count = 0
if (status == 0):
    print('Result: Success')
