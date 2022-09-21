from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
Count = 0
status = 0
obj = Solution()
for line in Lines:
    if line.strip() == 'true':
        line = 'True'
    if line.strip() == 'false':
        line = 'False'
    if (Count % 2 == 0):
        input = line.strip()
        actual_output = obj.isValid(line.strip())
    else:
        expected_output = line.strip()
        if (expected_output != str(actual_output)):
            print('Result: Failed')
            print("Input: s= {}".format(input))
            print("Actual Output:", actual_output)
            print("Expected Outuput:", expected_output)
            status = 1
            break
    Count += 1
if status == 0:
    print('Result: Success')
