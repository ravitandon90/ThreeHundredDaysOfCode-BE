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
        a, b = line.split()
        actual_output = obj.addBinary(a, b)
    else:
        expected_output = line.strip()
        if (expected_output != actual_output):
            print('Result: Failed')
            print('Actual Output:', actual_output)
            print('Expected Output:', expected_output)
            status = 1
            break
    Count += 1
if status == 0:
    print('Result: Success')
