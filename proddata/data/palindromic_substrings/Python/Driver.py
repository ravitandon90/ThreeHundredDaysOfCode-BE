from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
lineNo = 1
status = 0
obj = Solution()
for line in Lines:
    if (lineNo == 1):
        actual_output = obj.countSubstrings(line.strip())
        lineNo = 0
    else:
        expected_output = line.strip()
        lineNo = 1
        if (expected_output != str(actual_output)):
            print('Result: Failed')
            print('Actual Output:', actual_output)
            print('Expected Output:', expected_output)
            status = 1
            break
if status == 0:
    print('Result: Success')
