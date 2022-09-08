from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], 'testcases.txt'), 'r')
Lines = file1.readlines()
Count = 0
status = 0
obj = Solution()
for line in Lines:
    if (Count % 2 == 0):
        a = list(map(int, line.split()))
        actual_output = obj.threeSum(a)
    else:
        expected_output = line.strip()
        if (str(expected_output) != str(actual_output)):
            print('Actual Output:', actual_output)
            print('Expected Output:', expected_output)
            status = 1
            break
    Count += 1
if status == 0:
    print('Accepted')
